package com.hackathon.ahreview.data.service

import com.hackathon.ahreview.data.model.response.UserInfo
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ServerApi {
    @GET("user/info")
    fun getUserInfo(@Header("authorization") token: String): Single<Response<UserInfo>>

    @GET("store")
    fun getStoreList()

}