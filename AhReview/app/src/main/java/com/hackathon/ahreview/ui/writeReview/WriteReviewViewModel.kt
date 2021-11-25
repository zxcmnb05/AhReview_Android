package com.hackathon.ahreview.ui.writeReview

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.hackathon.ahreview.ui.base.BaseViewModel
import com.hackathon.ahreview.utils.SingleLiveEvent

class WriteReviewViewModel : BaseViewModel() {

    val onClickedAnonymous = SingleLiveEvent<Unit>()

    val anonymous = MutableLiveData<Boolean>(false)
    val rating = MutableLiveData<Float>()

    fun onCheckedAnonymous(view: View) {
        onClickedAnonymous.call()
    }
}