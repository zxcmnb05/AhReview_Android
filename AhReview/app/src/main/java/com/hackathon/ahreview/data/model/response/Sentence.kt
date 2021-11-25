package com.hackathon.ahreview.data.model.response


import com.google.gson.annotations.SerializedName

data class Sentence(
    val confidence: ConfidenceX,
    val content: String,
    val highlights: List<Highlight>,
    val length: Int,
    val offset: Int,
    val sentiment: String
)