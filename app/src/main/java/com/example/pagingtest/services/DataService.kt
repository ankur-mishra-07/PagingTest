package com.example.pagingtest.services

import com.example.pagingtest.data.FeedData
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService {
    @GET("api/v1/search?query=<sports>")
    suspend fun getData(@Query("page") pageNumber: Int): FeedData
}