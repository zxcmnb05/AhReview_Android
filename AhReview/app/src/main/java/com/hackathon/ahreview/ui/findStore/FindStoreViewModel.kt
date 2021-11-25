package com.hackathon.ahreview.ui.findStore

import com.hackathon.ahreview.ui.base.BaseViewModel
import com.hackathon.ahreview.utils.SingleLiveEvent

class FindStoreViewModel: BaseViewModel() {
    val backBtn = SingleLiveEvent<Any>()

    fun onClickBackBtn(){
        backBtn.call()
    }
}