package com.example.pagingtest

import com.example.pagingtest.common.NetworkModule
import com.example.pagingtest.data.FeedData

class FeedRepository {

    private val service = NetworkModule.getRetrofitInstance()

    suspend fun fetchContent(request: Int): FeedData {
        return service.getData(request)
    }
}