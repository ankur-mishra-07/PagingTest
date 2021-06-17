package com.example.pagingtest.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class DataSourceFactory: DataSource.Factory<Int, FeedData>() {
    val source = MutableLiveData<FeedDataSource>()
    override fun create(): DataSource<Int, FeedData> {
        val source = FeedDataSource()
        this.source.postValue(source)
        return source
    }
}