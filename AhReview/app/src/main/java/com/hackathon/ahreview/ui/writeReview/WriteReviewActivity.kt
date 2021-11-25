package com.hackathon.ahreview.ui.writeReview

import android.Manifest
import android.content.Intent
import android.net.Uri
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
import androidx.core.app.ActivityCompat

import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File

class WriteReviewActivity : BaseActivity<ActivityWriteReviewBinding, WriteReviewViewModel>() {
    override val viewModel: WriteReviewViewModel by viewModel()

    private val CLIENT_ID = "2g52oohbbm"
    private val PERMISSION = 1

    lateinit var handler: RecognitionHandler
    lateinit var naverRecognizer: NaverRecognizer

    lateinit var writer: AudioWriterPCM

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
            mBinding.rvWritePostImage.adapter = imageAdapter

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
            onOpenImagePickerEvent.observe(this@WriteReviewActivity, {
                val intent =
                    Intent().setType("image/*").putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                        .setAction(Intent.ACTION_PICK)
                getContent.launch(intent)
            })
            imageAdapter.imageList.observe(this@WriteReviewActivity, {
                imageAdapter.notifyDataSetChanged()
            })
            onReviewed.observe(this@WriteReviewActivity, {
                Log.e("별점", ratingStar.value.toString())
                Log.e("익명", anonymous.value.toString())
                Log.e("리뷰 내용", review.value.toString())

            })
        }
    }

    val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            it.data?.clipData?.apply {
                for (i in 0 until itemCount) {
                    mViewModel.imageAdapter.imageList.value?.add(
                        File(getRealPathFromURI(getItemAt(i).uri).path)
                    )
                    Log.e("image", mViewModel.imageAdapter.imageList.value.toString())
                }
            }
                ?: mViewModel.imageAdapter.imageList.value?.add(
                    File(getRealPathFromURI(it.data?.data ?: Uri.EMPTY).path)
                )

            mViewModel.imageAdapter.notifyListChanged()
        }
    }

    // uri의 절대 경로를 반환하는 메서드
    private fun getRealPathFromURI(uri: Uri): Uri {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = this.contentResolver.query(uri, filePathColumn, null, null, null)
        cursor?.moveToFirst()

        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val picturePath = columnIndex?.let { cursor.getString(it) }
        cursor?.close()

        return Uri.fromFile(File(picturePath ?: ""))
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