package com.example.pagingtest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pagingtest.FeedRepository
import com.example.pagingtest.common.*
import com.example.pagingtest.data.FeedData
import com.example.pagingtest.data.GenericError
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class FetchViewModel : ViewModel() {

    private var feedData: MutableLiveData<ViewState<FeedData>> = MutableLiveData()
    private val dataRepository = FeedRepository()

    fun getFeed(): MutableLiveData<ViewState<FeedData>> {
        return feedData
    }

    fun getDataFromSource(pageNo: Int) {
        feedData.value = Loading
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        // viewModelScope launch the new coroutine on Main Dispatcher internally
        viewModelScope.launch(coroutineExceptionHandler) {
            // Create User Registration
            val feed = dataRepository.fetchContent(pageNo)
            // Return the result on main thread via Dispatchers.Main
            feedData.value = Success(feed)
        }

    }

    private fun onError(throwable: Throwable) {
        val errorBody = try {
            (throwable as retrofit2.HttpException).response()?.errorBody()?.string()
        } catch (e: Exception) {
        }
        var message: GenericError = if (errorBody != null) {
            try {
                Gson().fromJson(errorBody.toString(), GenericError::class.java)
            } catch (e: Exception) {
                GenericError("", AppConstant.Generic_Error_Message, "")
            } as GenericError
        } else {
            GenericError("", AppConstant.Generic_Error_Message, "")
        }
        feedData.value = NetworkError(message.details)
    }


}