package com.hackathon.ahreview.data.model.response


import com.google.gson.annotations.SerializedName

data class StoreReview(
    val answer: String,
    val positive: Boolean,
    val review: String,
    @SerializedName("star_score")
    val starScore: Int,
    @SerializedName("url_list")
    val urlList: List<String>,
    var username: String = "익명"
)