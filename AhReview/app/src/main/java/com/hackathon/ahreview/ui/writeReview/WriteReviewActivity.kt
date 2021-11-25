package com.hackathon.ahreview.ui.writeReview

import android.Manifest
import android.os.*
import android.util.Log
import android.widget.RatingBar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hackathon.ahreview.R
import com.hackathon.ahreview.data.model.request.ReviewRequest
import com.hackathon.ahreview.data.util.SharedPreferenceManager
import com.hackathon.ahreview.databinding.ActivityWriteReviewBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import com.hackathon.ahreview.utils.AudioWriterPCM
import com.hackathon.ahreview.utils.NaverRecognizer
import com.naver.speech.clientapi.SpeechRecognitionResult
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.smartschool.morammoram.presentation.extension.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference
import java.util.*

class WriteReviewActivity : BaseActivity<ActivityWriteReviewBinding, WriteReviewViewModel>() {
    override val viewModel: WriteReviewViewModel by viewModel()

    private val CLIENT_ID = "2g52oohbbm"
    private val PERMISSION = 1

    lateinit var handler: RecognitionHandler
    lateinit var naverRecognizer: NaverRecognizer

    lateinit var writer: AudioWriterPCM

    lateinit var address: String

    val negativeAnswer = listOf(
        "좋은 리뷰 감사합니다~♥ 다음번에도 많은 이용 부탁드려요 ^3^",
        "리뷰 덕분에 힘이 되었습니다~ 앞으로도 고객님이 만족하실 수 있도록 최선을 다하겠습니다",
    )
    val positiveAnswer = listOf("이용에 불편을 드려 죄송합니다\uD83D\uDE2A 다음번에는 훨씬 더 좋은 품질로 보답드리겠습니다.",
        "아쉬우셨다고 하니, 저희도 마음이 아프네요 ㅠㅠ 다음엔 만족스러우실 수 있도록 최선을 다하겠습니다!", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= 23) {
            // 퍼미션 체크
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.INTERNET,
                    Manifest.permission.RECORD_AUDIO
                ), PERMISSION
            )
        }

        address = intent.getStringExtra("address")
        mBinding.storeItem.storeTvStoreName.text = intent.getStringExtra("name")
        mBinding.storeItem.tvLocation.text = intent.getStringExtra("address")
        mBinding.storeItem.storeTvStoreReview.text = "리뷰 ${intent.getIntExtra("count", 0)}개"
        mBinding.storeItem.storeTvAverage.text = "${intent.getIntExtra("score", 0)}.0"

        Glide.with(this)
            .load(intent.getStringExtra("url"))
            .into(mBinding.storeItem.storeImage)

        writer = AudioWriterPCM(
            Environment.getExternalStorageDirectory().absolutePath
                .toString() + "/NaverSpeechTest"
        )
        handler = RecognitionHandler(this)
        naverRecognizer = NaverRecognizer(this, handler, CLIENT_ID)
    }

    override fun observerViewModel() {

        mBinding.ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                viewModel.ratingStar.value = rating
            }
        with(viewModel) {

            sentimentSuccess.observe(this@WriteReviewActivity, Observer {
                val token = SharedPreferenceManager.getToken(applicationContext)

                if (token != null) {
                    val url = ArrayList<String>()
                    url.add(imageUrl.value!!)

                    if (it.equals("negative")) {
                        addDisposable(viewModel.reviewRepository.postReview(
                            "Bearer $token",
                            reviewRequest = ReviewRequest(
                                address = address,
                                anonymous = anonymous.value!!,
                                answer = getMessage(false),
                                positive = false,
                                review = review.value.toString(),
                                star_score = ratingStar.value!!.toInt(),
                                url_list = url
                            )
                        ),
                            object : DisposableSingleObserver<Any>() {
                                override fun onSuccess(t: Any) {
                                    finish()
                                }

                                override fun onError(e: Throwable) {
                                    shortToast("다시 시도해주세요")
                                }

                            })
                    } else {
                        addDisposable(viewModel.reviewRepository.postReview(
                            "Bearer $token",
                            reviewRequest = ReviewRequest(
                                address = address,
                                anonymous = anonymous.value!!,
                                answer = getMessage(true),
                                positive = true,
                                review = review.value.toString(),
                                star_score = ratingStar.value!!.toInt(),
                                url_list = url
                            )
                        ),
                            object : DisposableSingleObserver<Any>() {
                                override fun onSuccess(t: Any) {
                                    finish()
                                }

                                override fun onError(e: Throwable) {
                                    shortToast("다시 시도해주세요")
                                }

                            })
                    }


                } else {
                    shortToast("토큰이 존재하지 않습니다.")
                }
            })

            onClickedAnonymous.observe(this@WriteReviewActivity, {
                anonymous.value = anonymous.value != true
            })

            onMicClicked.observe(this@WriteReviewActivity, {
                if (!naverRecognizer.getSpeechRecognizer()!!.isRunning) {
                    naverRecognizer.recognize()
                } else {
                    naverRecognizer.getSpeechRecognizer()!!.stop()
                }

            })
            onReviewed.observe(this@WriteReviewActivity, {
                getSentiment(resources.getString(R.string.clova_client_id),
                    resources.getString(R.string.clova_client_secret),
                    resources.getString(R.string.json_type),
                    review.value!!)
            })
        }
    }

    fun getMessage(check: Boolean): String {
        val i = Math.random().toInt() * 3

        return if (check) {
            positiveAnswer[i]
        } else {
            negativeAnswer[i]
        }
    }

    class RecognitionHandler(activity: WriteReviewActivity?) : Handler() {
        private val mActivity: WeakReference<WriteReviewActivity> =
            WeakReference<WriteReviewActivity>(activity)

        override fun handleMessage(msg: Message?) {
            val activity: WriteReviewActivity = mActivity.get()!!
            if (activity != null) {
                activity.handleMessage(msg!!)
            }
        }

    }

    private fun handleMessage(msg: Message) {
        when (msg.what) {
            R.id.clientReady -> {
                // Now an user can speak.
                shortToast("연결되었습니다 말해주세요")
                writer.open("Test")
            }
            R.id.audioRecording -> writer.write(msg.obj as ShortArray)
            R.id.partialResult -> {
                // Extract obj property typed with String.
            }
            R.id.finalResult -> {
                // Extract obj property typed with String array.
                // The first element is recognition result for speech.
                val speechRecognitionResult = msg.obj as SpeechRecognitionResult
                val results = speechRecognitionResult.results
                val strBuf = StringBuilder()
                for (result in results) {
                    strBuf.append(result + " ")
                    break
                }
                viewModel.review.value += strBuf.toString()
                Log.d("Result : ", strBuf.toString())
            }
            R.id.recognitionError -> {
                if (writer != null) {
                    writer.close()
                }
                Log.d("Error code : ", msg.obj.toString())
            }
            R.id.clientInactive -> {
                if (writer != null) {
                    writer.close()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        naverRecognizer.getSpeechRecognizer()!!.initialize()
    }

    override fun onStop() {
        super.onStop()
        naverRecognizer.getSpeechRecognizer()!!.release()
    }
}