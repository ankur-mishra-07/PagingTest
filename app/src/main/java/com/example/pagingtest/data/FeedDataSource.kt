package com.example.pagingtest.data

import android.support.annotation.NonNull
import androidx.paging.PageKeyedDataSource
import com.example.pagingtest.services.DataService


class FeedDataSource : PageKeyedDataSource<Int, FeedData>() {
    var dataService: DataService? = null
    override fun loadInitial(
        @NonNull params: LoadInitialParams<Int>,
        @NonNull callback: LoadInitialCallback<Int, FeedData>
    ) {

    }

    override fun loadBefore(
        @NonNull params: LoadParams<Int>,
        @NonNull callback: LoadCallback<Int, FeedData>
    ) {
    }

    override fun loadAfter(
        @NonNull params: LoadParams<Int>,
        @NonNull callback: LoadCallback<Int, FeedData>
    ) {
    }
}
