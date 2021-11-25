package com.hackathon.ahreview.data.repository

import com.hackathon.ahreview.data.Server
import com.hackathon.ahreview.data.model.response.Store
import com.hackathon.ahreview.data.model.response.UserInfo
import io.reactivex.Single
import org.json.JSONObject

class ServerRepository {
    fun getUserInfo(token: String): Single<UserInfo> {
        return Server.serverRetrofit.getUserInfo(token).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.body().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()
        }
    }

    fun getStoreList(): Single<List<Store>> {
        return Server.serverRetrofit.getStoreList().map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.body().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()
        }
    }

}