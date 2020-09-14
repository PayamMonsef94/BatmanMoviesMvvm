package com.example.batmanmoviesmvvm.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.batmanmoviesmvvm.data.model.Movie
import com.example.batmanmoviesmvvm.ui.main.MovieAdapter

/*@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(visible: Boolean) {
    isRefreshing = visible
}*/

/*@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Task>?) {
    items?.let {
        (listView.adapter as TasksAdapter).submitList(items)
    }
}*/

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Movie>?) {
    items?.let {
        (listView.adapter as MovieAdapter).submitList(items)
    }
}
