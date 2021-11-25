package com.hackathon.ahreview.ui.main

import androidx.lifecycle.MutableLiveData
import com.hackathon.ahreview.ui.base.BaseViewModel
import com.hackathon.ahreview.utils.SingleLiveEvent

class MainViewModel : BaseViewModel() {
    val userName = MutableLiveData<String>()
    val findStoreBtn = SingleLiveEvent<Any>()

    fun onClickFindStoreBtn(){
        findStoreBtn.call()
    }

}