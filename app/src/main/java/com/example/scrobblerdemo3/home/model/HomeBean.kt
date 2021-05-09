package com.example.scrobblerdemo3.home.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeBean(
    val albumId: Int,
    val thumbnailUrl: String,
    val id: Int,
    val title: String
)