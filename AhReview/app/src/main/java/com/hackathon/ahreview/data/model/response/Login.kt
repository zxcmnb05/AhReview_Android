package com.hackathon.ahreview.data.model.response


import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)