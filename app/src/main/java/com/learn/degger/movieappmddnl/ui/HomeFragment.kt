package com.learn.degger.movieappmddnl.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learn.degger.movieappmddnl.adapters.MovieAdapters
import com.learn.degger.movieappmddnl.adapters.ViewPagerAdapter
import com.learn.degger.movieappmddnl.databinding.FragmentHomeBinding
import com.learn.degger.movieappmddnl.model.Movie
import com.learn.degger.movieappmddnl.utilis.ApiState
import com.learn.degger.movieappmddnl.utilis.Constants
import com.learn.degger.movieappmddnl.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val  has : HashMap<String ,String> = HashMap()
    private val mainViewModel: HomeViewModel by viewModels()
    lateinit var binding: FragmentHomeBinding
    private var movieAdapter: MovieAdapters? = null
    private var TopRatedMovieAdapter: MovieAdapters? = null
    private var RatedMovies: ArrayList<Movie>? = null
    private var currentlyShowingAdapter: ViewPagerAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        RatedMovies = ArrayList()
        initRecyelerview()
        has.put("api_key", Constants.API_KEY)
        has.put("page", "1")

        mainViewModel.getTopRatedMovies(has)

        return binding.root
    }
    private fun initRecyelerview() {
        binding.topRatedRecyclerView.setHasFixedSize(true)
        binding.topRatedRecyclerView.layoutManager = LinearLayoutManager(
            context, RecyclerView.HORIZONTAL, false
        )
    }

    private fun getRatedMovies() {
        val notesObserver: Observer<List<Movie>> =
            Observer<List<Movie>> {
                    notes: List<Movie> ->

                RatedMovies!!.clear()
                RatedMovies!!.addAll(notes)
                if (TopRatedMovieAdapter == null) {
                    if (RatedMovies != null) {
                        TopRatedMovieAdapter =
                            context?.let { MovieAdapters(it,notes) }
                        binding.topRatedRecyclerView.adapter = TopRatedMovieAdapter
                    } else {
                        Toast.makeText(activity, "" + "empty", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    TopRatedMovieAdapter!!.notifyDataSetChanged()
                }
            }

        mainViewModel.getTopRatedMoviesList().observe(requireActivity(), notesObserver)
    }
    override fun onResume() {
        super.onResume()
        getPopularMovies()
        getupcomingShowing()
        getRatedMovies()


    }

    private fun getupcomingShowing() {
        has.put("api_key", Constants.API_KEY)
        has.put("page", "1")
        mainViewModel.getUpComingsMovies(has)
        lifecycleScope.launchWhenStarted {
            binding.apply {
                mainViewModel.phoneApiState.collect {
                    when(it){
                        is ApiState.Success -> {
                            if (it.data.isEmpty()){
                                Log.d("dd","jjd")
                            }else{
                                binding.upcomingRecyclerView.layoutManager = LinearLayoutManager(
                                    context, RecyclerView.HORIZONTAL, false
                                )

                                movieAdapter = context?.let { it1 -> MovieAdapters(it1, it.data) }
                                binding.upcomingRecyclerView.adapter = movieAdapter


                            }

                            //

                        }
                        is ApiState.Failure -> {

                            Log.d("main", "getPhone: ")
                        }
                        is ApiState.Loading -> {

                        }
                        is ApiState.Empty -> {

                        }
                    }
                }
            }
        }
    }

    private fun getPopularMovies() {
        has.put("api_key", Constants.API_KEY)
        has.put("page", "1")
        mainViewModel.getPopularMovies(has)
        lifecycleScope.launchWhenStarted {
            binding.apply {
                mainViewModel.phoneApiState.collect {
                    when(it){
                        is ApiState.Success -> {
                            if (it.data.isEmpty()){
                                Log.d("dd","jjd")
                            }else{
                                binding.popularRecyclerView.layoutManager = LinearLayoutManager(
                                    context, RecyclerView.HORIZONTAL, false
                                )

                                movieAdapter = context?.let { it1 -> MovieAdapters(it1, it.data) }
                                binding.popularRecyclerView.adapter = movieAdapter

                                currentlyShowingAdapter = ViewPagerAdapter(requireContext(), it.data)
                                binding.currentlyShowingViewPager.adapter = currentlyShowingAdapter
                            }

                         //

                        }
                        is ApiState.Failure -> {

                            Log.d("main", "getPhone: ")
                        }
                        is ApiState.Loading -> {

                        }
                        is ApiState.Empty -> {

                        }
                    }
                }
            }
        }
    }

}
