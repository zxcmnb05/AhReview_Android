package com.hackathon.ahreview.data.model.response


import com.google.gson.annotations.SerializedName

data class Store(
    val address: String,
    val name: String,
    @SerializedName("review_amount")
    val reviewAmount: Int,
    @SerializedName("star_score")
    val starScore: Int,
    val url: String
)