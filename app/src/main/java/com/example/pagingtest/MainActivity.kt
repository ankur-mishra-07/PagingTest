package com.example.pagingtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingtest.common.Loading
import com.example.pagingtest.common.NetworkError
import com.example.pagingtest.common.Success
import com.example.pagingtest.common.ViewState
import com.example.pagingtest.data.FeedData
import com.example.pagingtest.viewmodel.FetchViewModel

class MainActivity : AppCompatActivity(), FeedAdapter.OnClickListener {
    lateinit var photoRecylerview: RecyclerView
    lateinit var adapter: FeedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(FetchViewModel::class.java)
        photoRecylerview = findViewById(R.id.feedList)
        photoRecylerview.layoutManager = LinearLayoutManager(this)
        adapter = FeedAdapter(this)
        viewModel.getDataFromSource(1)
        viewModel.getFeed().observe(this, { onFeedReceived(it) })
    }

    private fun onFeedReceived(viewState: ViewState<FeedData>?) {
        when (viewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
            }
            is Success -> {
                setProgress(false)
                photoRecylerview.adapter = adapter
                adapter.updateList(viewState.mData)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun setProgress(isLoading: Boolean) {

    }

    override fun whenListIsClicked(url: String) {

    }

}