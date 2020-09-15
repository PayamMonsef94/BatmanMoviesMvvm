package com.example.batmanmoviesmvvm.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.batmanmoviesmvvm.R
import com.example.batmanmoviesmvvm.data.model.Movie
import com.example.batmanmoviesmvvm.ui.main.MovieAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:items")
fun items(listView: RecyclerView, items: List<Movie>?) {
    items?.let {
        (listView.adapter as MovieAdapter).submitList(items)
    }
}

@BindingAdapter("app:bindPicture")
fun bindPicture(imageView: ImageView, url: String?) {
    url?.let {
        Picasso.get().load(url).placeholder(R.drawable.holder).into(imageView)
    }
}
