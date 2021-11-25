package com.hackathon.ahreview.data.repository

import com.hackathon.ahreview.data.Server
import com.hackathon.ahreview.data.model.request.ReviewRequest
import io.reactivex.Single
import org.json.JSONObject
import retrofit2.Response

class ReviewRepository {
    fun postReview(token: String, reviewRequest: ReviewRequest): Single<Any> {
        return Server.serverRetrofit.postReview(token, reviewRequest).map{
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.body().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()
        }
    }
}