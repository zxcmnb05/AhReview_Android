package com.hackathon.ahreview.data.model.response


import com.google.gson.annotations.SerializedName

data class Document(
    val confidence: Confidence,
    val sentiment: String
)