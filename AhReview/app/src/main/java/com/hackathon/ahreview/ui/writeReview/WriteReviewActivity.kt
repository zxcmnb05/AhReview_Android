package com.hackathon.ahreview.ui.writeReview

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.Environment
import android.widget.RatingBar
import com.hackathon.ahreview.databinding.ActivityWriteReviewBinding
import com.hackathon.ahreview.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference
import com.hackathon.ahreview.R
import android.util.Log

import com.naver.speech.clientapi.SpeechRecognitionResult
import com.hackathon.ahreview.utils.AudioWriterPCM

import com.hackathon.ahreview.utils.NaverRecognizer
import kr.hs.dgsw.smartschool.morammoram.presentation.extension.shortToast
import java.lang.StringBuilder


class WriteReviewActivity : BaseActivity<ActivityWriteReviewBinding, WriteReviewViewModel>() {
    override val viewModel: WriteReviewViewModel by viewModel()

    private val CLIENT_ID = resources.getString(R.string.clova_client_id)

    lateinit var handler: RecognitionHandler
    lateinit var naverRecognizer: NaverRecognizer

    lateinit var writer: AudioWriterPCM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handler = RecognitionHandler(this)
        naverRecognizer = NaverRecognizer(this, handler, CLIENT_ID)
    }

    override fun observerViewModel() {

        mBinding.ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                viewModel.rating.value = rating
            }
        with(viewModel) {
            onClickedAnonymous.observe(this@WriteReviewActivity, {
                anonymous.value = anonymous.value != true
            })

            onMicClicked.observe(this@WriteReviewActivity, {
                onMic.value != onMic.value
                if (onMic.value == false){
                    naverRecognizer.recognize()
                } else {
                    naverRecognizer.stop();
                }

            })
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
                shortToast("Connected")
                writer = AudioWriterPCM(
                    Environment.getExternalStorageDirectory().absolutePath
                        .toString() + "/NaverSpeechTest"
                )
                writer.open("Test")
            }
            R.id.audioRecording -> writer.write(msg.obj as ShortArray)
            R.id.partialResult -> {
                // Extract obj property typed with String.
                Log.d("Result : ", msg.obj as String)
            }
            R.id.finalResult -> {
                // Extract obj property typed with String array.
                // The first element is recognition result for speech.
                val speechRecognitionResult = msg.obj as SpeechRecognitionResult
                val results = speechRecognitionResult.results
                val strBuf = StringBuilder()
                for (result in results) {
                    strBuf.append(result)
                    strBuf.append("\n")
                }
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

        naverRecognizer.mRecognizer
    }
}