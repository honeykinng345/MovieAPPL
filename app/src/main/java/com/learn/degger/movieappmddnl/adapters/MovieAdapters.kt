package com.learn.degger.movieappmddnl.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.learn.degger.movieappmddnl.databinding.MovieItemsBinding
import com.learn.degger.movieappmddnl.model.Movie
import com.learn.degger.movieappmddnl.ui.HomeFragmentDirections

class MovieAdapters(private val context: Context, private val dataList: List<Movie>)
    : RecyclerView.Adapter<MovieAdapters.BindingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val rootView: ViewDataBinding = MovieItemsBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(rootView)
    }
    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val product = dataList[position]
        holder.itemBinding.setVariable(androidx.databinding.library.baseAdapters.BR.MovieListItem,product)


   holder.itemView.setOnClickListener {
       val action = HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment(product)
       Navigation.findNavController(it).navigate(action)
   }
    }



    override fun getItemCount() =  dataList.size
    class BindingViewHolder(val itemBinding: ViewDataBinding)
        : RecyclerView.ViewHolder(itemBinding.root)
}