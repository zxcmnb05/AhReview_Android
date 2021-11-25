package com.hackathon.ahreview.ui.detailStore

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.hackathon.ahreview.ui.base.BaseViewModel
import com.hackathon.ahreview.utils.SingleLiveEvent

class DetailStoreViewModel:BaseViewModel() {

    val onClickWriteReview = SingleLiveEvent<Unit>()

    fun onClickWriteReview(view: View) {
        onClickWriteReview.call()
    }
        
    val backBtn = SingleLiveEvent<Any>()

    fun onClickBackBtn(){
        backBtn.call()
    }
}