package com.example.pagingtest

import android.R
import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingtest.data.FeedData
class FeedAdapter(private val callback: OnClickListener) : RecyclerView.Adapter<ViewHolder>(){

    interface OnClickListener {
        fun whenListIsClicked(url : String)
    }

    private lateinit var feedList: FeedData

    fun updateList(feedList: FeedData){
        this.feedList = feedList
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view: View = layoutInflater.inflate(R.layout.activity_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull photoViewHolder: ViewHolder, i: Int) {
        photoViewHolder.data.text = feedList.hits[i].author
    }

    override fun getItemCount(): Int {
        return feedList.hits.size
    }
}

class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
    var data: TextView = itemView.findViewById(R.id.text1)
}

class DiffUtilCallBack : DiffUtil.ItemCallback<FeedData>() {
    override fun areItemsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
        return oldItem.hits == newItem.hits
    }

    override fun areContentsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
        return oldItem.query == newItem.query
                && oldItem.page == newItem.page
                && oldItem.hits == newItem.hits
    }

}
