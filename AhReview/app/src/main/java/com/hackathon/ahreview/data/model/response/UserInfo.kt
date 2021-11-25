package com.hackathon.ahreview.data.model.response


import com.google.gson.annotations.SerializedName

data class UserInfo(
    val email: String,
    val name: String,
    val profile: String,
    @SerializedName("review_info_list")
    val reviewInfoList: List<ReviewInfo>
)