package com.hackathon.ahreview.data.model.response


import com.google.gson.annotations.SerializedName

data class SentimentResponse(
    val document: Document,
    val sentences: List<Sentence>
)