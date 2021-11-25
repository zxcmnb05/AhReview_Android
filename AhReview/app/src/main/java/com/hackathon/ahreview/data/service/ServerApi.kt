package com.hackathon.ahreview.data.service

import com.hackathon.ahreview.data.model.request.TokenLoginRequest
import com.hackathon.ahreview.data.model.response.Login
import com.hackathon.ahreview.data.model.response.Store
import com.hackathon.ahreview.data.model.response.UserInfo
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ServerApi {
    @GET("user/info")
    fun getUserInfo(@Header("authorization") token: String): Single<Response<UserInfo>>

    @GET("store")
    fun getStoreList(@Header("authorization") token: String): Single<Response<List<Store>>>

    @POST("login/token")
    fun tokenLogin(@Body request: TokenLoginRequest): Single<Response<Login>>
}