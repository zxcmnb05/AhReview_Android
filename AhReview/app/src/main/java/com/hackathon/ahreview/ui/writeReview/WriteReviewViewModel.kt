package com.hackathon.ahreview.ui.writeReview

import android.speech.RecognitionListener
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.hackathon.ahreview.ui.base.BaseViewModel
import com.hackathon.ahreview.utils.SingleLiveEvent

class WriteReviewViewModel : BaseViewModel() {

    val onClickedAnonymous = SingleLiveEvent<Unit>()
    val onMicClicked = SingleLiveEvent<Unit>()
    val onMic = MutableLiveData<Boolean>(false)

    val anonymous = MutableLiveData<Boolean>(false)
    val review = MutableLiveData<String>("")
    val rating = MutableLiveData<Float>()

    fun onCheckedAnonymous(view: View) {
        onClickedAnonymous.call()
    }

    fun onMicClicked(view: View) {
        onMicClicked.call()
    }
}