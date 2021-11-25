package com.hackathon.ahreview.data.repository

import com.hackathon.ahreview.data.Server
import com.hackathon.ahreview.data.model.request.GetReviewRequest
import com.hackathon.ahreview.data.model.request.ReviewRequest
import com.hackathon.ahreview.data.model.response.StoreReview
import io.reactivex.Single
import org.json.JSONObject

class ReviewRepository {
    fun postReview(token: String, reviewRequest: ReviewRequest): Single<Any> {
        return Server.serverRetrofit.postReview(token, reviewRequest).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.body().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()
        }
    }

    fun getReview(token: String, filter: Int, request: GetReviewRequest): Single<List<StoreReview>> {
        return Server.serverRetrofit.getReview(token, filter, request).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.body().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()
        }
    }
}