package com.hackathon.ahreview.ui.test

import androidx.lifecycle.MutableLiveData
import com.hackathon.ahreview.data.model.response.SentimentResponse
import com.hackathon.ahreview.data.repository.ClovaRepository
import com.hackathon.ahreview.ui.base.BaseViewModel
import com.hackathon.ahreview.utils.SingleLiveEvent
import io.reactivex.observers.DisposableSingleObserver

class TestViewModel(private val clovaRepository: ClovaRepository) : BaseViewModel() {
    val input = MutableLiveData<String>()
    val result = MutableLiveData<String>()
    val btn = SingleLiveEvent<Any>()

    fun onClickBtn() {
        btn.call()
    }

    fun check(id: String, key: String, type: String, content: String) {
        addDisposable(clovaRepository.checkSentiment(id, key, type, content),
            object : DisposableSingleObserver<SentimentResponse>() {
                override fun onSuccess(t: SentimentResponse) {
                    result.value = t.document.sentiment
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }
}