package com.learn.degger.movieappmddnl.bindAdapers

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learn.degger.movieappmddnl.adapters.MovieAdapters
import com.learn.degger.movieappmddnl.model.Movie

@BindingAdapter("viee")
fun bindRecyclerViewList(view: RecyclerView, dataList: List<Movie>) {
    if (dataList.isEmpty()){
        return
    }

    val layoutManager = view.layoutManager
    if (layoutManager == null)
        view.layoutManager = GridLayoutManager(view.context, 2)

    var adapter = view.adapter

    if (adapter == null) {
        adapter = MovieAdapters(view.context, dataList)
        view.adapter = adapter
    }

}