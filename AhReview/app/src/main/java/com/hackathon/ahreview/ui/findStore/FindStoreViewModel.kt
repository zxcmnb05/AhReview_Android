package com.hackathon.ahreview.ui.findStore

import androidx.lifecycle.MutableLiveData
import com.hackathon.ahreview.data.model.response.Store
import com.hackathon.ahreview.data.repository.ServerRepository
import com.hackathon.ahreview.ui.base.BaseViewModel
import com.hackathon.ahreview.utils.SingleLiveEvent
import io.reactivex.observers.DisposableSingleObserver

class FindStoreViewModel(private val serverRepository: ServerRepository) : BaseViewModel() {
    val backBtn = SingleLiveEvent<Any>()

    val getStoreListSuccess = MutableLiveData<List<Store>>()
    val getStoreListError = MutableLiveData<Throwable>()

    fun getStoreList() {
        addDisposable(serverRepository.getStoreList(),
            object : DisposableSingleObserver<List<Store>>() {
                override fun onSuccess(t: List<Store>) {
                    getStoreListSuccess.value = t
                }

                override fun onError(e: Throwable) {
                    getStoreListError.value = e
                }

            })
    }

    fun onClickBackBtn() {
        backBtn.call()
    }
}