package com.hackathon.ahreview.data.service

import com.hackathon.ahreview.data.model.request.SentimentRequest
import com.hackathon.ahreview.data.model.response.SentimentResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ClovaApi {
    @POST("sentiment-analysis/v1/analyze")
    fun checkSentiment(
        @Header("X-NCP-APIGW-API-KEY-ID") id: String,
        @Header("X-NCP-APIGW-API-KEY") key: String,
        @Header("Content-Type") type: String,
        @Body request: SentimentRequest
    ): Single<Response<SentimentResponse>>
}