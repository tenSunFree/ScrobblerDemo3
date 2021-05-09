package com.example.scrobblerdemo3.home.model

import retrofit2.http.GET

interface HomeService {
    @GET("photos")
    suspend fun getData(): List<HomeBean>
}