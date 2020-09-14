package com.example.batmanmoviesmvvm.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.batmanmoviesmvvm.R
import com.example.batmanmoviesmvvm.data.model.Movie
import com.example.batmanmoviesmvvm.ui.main.MovieAdapter
import com.squareup.picasso.Picasso

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

@BindingAdapter("app:bindPicture")
fun bindPicture(imageView: ImageView, url: String?) {
    url?.let {
        Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground).into(imageView)
    }
}
