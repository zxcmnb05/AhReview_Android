package com.hackathon.ahreview.data.repository

import com.hackathon.ahreview.data.ClovaServer
import com.hackathon.ahreview.data.model.request.SentimentRequest
import com.hackathon.ahreview.data.model.response.SentimentResponse
import io.reactivex.Single
import org.json.JSONObject

class ClovaRepository {
    fun checkSentiment(id: String, key: String, type: String, content: String): Single<SentimentResponse> {
        val request = SentimentRequest(content)
        return ClovaServer.clovaRetrofit.checkSentiment(id, key, type, request).map{
            if(!it.isSuccessful){
                val errorBody = JSONObject(it.body().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()
        }
    }
}