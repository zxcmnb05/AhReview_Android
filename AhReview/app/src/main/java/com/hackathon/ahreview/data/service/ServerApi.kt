package com.hackathon.ahreview.data.service

import com.hackathon.ahreview.data.model.request.GetReviewRequest
import com.hackathon.ahreview.data.model.request.ReviewRequest
import com.hackathon.ahreview.data.model.request.TokenLoginRequest
import com.hackathon.ahreview.data.model.response.Login
import com.hackathon.ahreview.data.model.response.Store
import com.hackathon.ahreview.data.model.response.StoreReview
import com.hackathon.ahreview.data.model.response.UserInfo
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface ServerApi {
    @GET("user/info")
    fun getUserInfo(@Header("authorization") token: String): Single<Response<UserInfo>>

    @GET("store")
    fun getStoreList(@Header("authorization") token: String): Single<Response<List<Store>>>

    @POST("login/token")
    fun tokenLogin(@Body request: TokenLoginRequest): Single<Response<Login>>


    @POST("review")
    fun postReview(
        @Header("authorization") token: String,
        @Body reviewRequest: ReviewRequest,
    ): Single<Response<Any>>

    @GET("review")
    fun getReview(
        @Header("authorization") token: String,
        @Query("filter") filter: Int,
        @Query("address") address: String,
    ): Single<Response<List<StoreReview>>>
}