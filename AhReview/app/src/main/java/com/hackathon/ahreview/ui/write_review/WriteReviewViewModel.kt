package com.hackathon.ahreview.ui.write_review

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.hackathon.ahreview.ui.base.BaseViewModel

class WriteReviewViewModel: BaseViewModel() {
    val anonymous = MutableLiveData<Boolean>(false)
    val rating = MutableLiveData<Float>()
}