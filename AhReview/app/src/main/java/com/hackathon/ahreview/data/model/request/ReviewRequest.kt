package com.hackathon.ahreview.data.model.request

import java.io.File

data class ReviewRequest(
    val address: String,
    val anonymous: Boolean,
    val answer: String,
    val positive: Boolean,
    val review: String,
    val star_score: Int,
    val url_list: List<String>
)
