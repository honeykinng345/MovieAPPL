package com.learn.degger.movieappmddnl.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.learn.degger.movieappmddnl.databinding.CurrentlyShowingMovieItemBinding
import com.learn.degger.movieappmddnl.model.Movie

class ViewPagerAdapter(private val context: Context, private val dataList: List<Movie>)
    : RecyclerView.Adapter<ViewPagerAdapter.BindingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val inflater = LayoutInflater.from(context)
        val rootView: ViewDataBinding= CurrentlyShowingMovieItemBinding.inflate(inflater, parent, false)
        return BindingViewHolder(rootView)
    }
    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val product = dataList[position]
        holder.itemBinding.setVariable(androidx.databinding.library.baseAdapters.BR.MovieListItem,product)

/*
   holder.itemView.setOnClickListener {
       val action = HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment(product)
       Navigation.findNavController(it).navigate(action)
   }*/
    }



    override fun getItemCount() =  dataList.size
    class BindingViewHolder(val itemBinding: ViewDataBinding)
        : RecyclerView.ViewHolder(itemBinding.root)
}