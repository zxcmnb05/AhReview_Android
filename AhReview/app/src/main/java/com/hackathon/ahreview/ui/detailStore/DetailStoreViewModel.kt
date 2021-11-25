package com.hackathon.ahreview.ui.detailStore

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.hackathon.ahreview.data.model.request.GetReviewRequest
import com.hackathon.ahreview.data.model.response.StoreReview
import com.hackathon.ahreview.data.repository.ReviewRepository
import com.hackathon.ahreview.ui.base.BaseViewModel
import com.hackathon.ahreview.utils.SingleLiveEvent
import io.reactivex.observers.DisposableSingleObserver

class DetailStoreViewModel(private val reviewRepository: ReviewRepository) : BaseViewModel() {

    val onClickWriteReview = SingleLiveEvent<Unit>()

    val backBtn = SingleLiveEvent<Any>()

    val getListSuccess = MutableLiveData<List<StoreReview>>()
    val getListError = MutableLiveData<Throwable>()

    val recentBtn = SingleLiveEvent<Any>()
    val positiveBtn = SingleLiveEvent<Any>()
    val negativeBtn = SingleLiveEvent<Any>()

    fun onClickWriteReview(view: View) {
        onClickWriteReview.call()
    }

    fun onClickBackBtn() {
        backBtn.call()
    }

    fun onClickRecentBtn() {
        recentBtn.call()
    }

    fun onClickPositiveBtn() {
        positiveBtn.call()
    }

    fun onClickNegativeBtn() {
        negativeBtn.call()
    }

    fun getReviewList(token: String, filter: Int, address: String) {
        val request = GetReviewRequest(address)
        addDisposable(reviewRepository.getReview(token, filter, request),
            object : DisposableSingleObserver<List<StoreReview>>() {

                override fun onSuccess(t: List<StoreReview>) {
                    getListSuccess.value = t

                }

                override fun onError(e: Throwable) {
                    getListError.value = e
                    e.printStackTrace()
                }

            })
    }
}