package com.hackathon.ahreview.data.model.response


import com.google.gson.annotations.SerializedName

data class Confidence(
    val negative: Double,
    val neutral: Double,
    val positive: Double
)