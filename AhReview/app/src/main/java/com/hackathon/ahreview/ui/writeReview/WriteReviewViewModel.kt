package com.hackathon.ahreview.ui.writeReview

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.hackathon.ahreview.data.model.response.SentimentResponse
import com.hackathon.ahreview.data.repository.ClovaRepository
import com.hackathon.ahreview.data.repository.ReviewRepository
import com.hackathon.ahreview.ui.base.BaseViewModel
import com.hackathon.ahreview.utils.SingleLiveEvent
import io.reactivex.observers.DisposableSingleObserver

class WriteReviewViewModel(
    val reviewRepository: ReviewRepository,
    private val clovaRepository: ClovaRepository,
) : BaseViewModel() {

    val onClickedAnonymous = SingleLiveEvent<Unit>()
    val onMicClicked = SingleLiveEvent<Unit>()
    val onReviewed = SingleLiveEvent<Unit>()

    val anonymous = MutableLiveData<Boolean>(false)
    val review = MutableLiveData<String>("")
    val ratingStar = MutableLiveData<Float>(0.0F)
    val imageUrl = MutableLiveData<String>("")

    val sentimentSuccess = MutableLiveData<SentimentResponse>()
    val sentimentError = MutableLiveData<Throwable>()

    fun getSentiment(id: String, key: String, type: String, review: String) {
        addDisposable(clovaRepository.checkSentiment(id, key, type, review), object: DisposableSingleObserver<SentimentResponse>(){
            override fun onSuccess(t: SentimentResponse) {
                sentimentSuccess.value = t
            }

            override fun onError(e: Throwable) {
                sentimentError.value = e
                e.printStackTrace()
            }
        })
    }

    fun onCheckedAnonymous(view: View) {
        onClickedAnonymous.call()
    }

    fun onMicClicked(view: View) {
        onMicClicked.call()
    }

    fun onReviewChecked(view: View) {
        onReviewed.call()
    }
}