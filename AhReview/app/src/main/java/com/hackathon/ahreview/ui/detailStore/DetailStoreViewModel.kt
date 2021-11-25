package com.hackathon.ahreview.ui.detailStore

import com.hackathon.ahreview.ui.base.BaseViewModel
import com.hackathon.ahreview.utils.SingleLiveEvent

class DetailStoreViewModel:BaseViewModel() {

    val backBtn = SingleLiveEvent<Any>()

    fun onClickBackBtn(){
        backBtn.call()
    }
}