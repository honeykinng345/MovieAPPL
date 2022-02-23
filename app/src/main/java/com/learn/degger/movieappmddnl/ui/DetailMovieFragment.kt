package com.learn.degger.movieappmddnl.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.learn.degger.movieappmddnl.databinding.FragmentMovieDetailBinding
import com.learn.degger.movieappmddnl.ui.dialoug.VideoDialog
import com.learn.degger.movieappmddnl.utilis.ApiState
import com.learn.degger.movieappmddnl.utilis.Constants
import com.learn.degger.movieappmddnl.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {
    private  val  args: DetailMovieFragmentArgs by navArgs()
    private val  queryMap : HashMap<String ,String> = HashMap()
    private val mainViewModel: HomeViewModel by viewModels()
    lateinit var binding: FragmentMovieDetailBinding
    var  videoId : String? = "Dh7SZ5xDb00"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        queryMap.put("api_key", Constants.API_KEY)
        queryMap.put("page", "1")
        queryMap.put("append_to_response", "videos")
    }

    override fun onResume() {
        super.onResume()
        val movieID =  +args.movieObject.id

        binding.playTrailer.setOnClickListener {
            if (videoId != null) {
                val dialog = VideoDialog(videoId!!)
                dialog.show(parentFragmentManager, "Video Dialog")
            } else Toast.makeText(context, "Sorry trailer not found!", Toast.LENGTH_SHORT).show()
        }
        getMovieDetail(movieID);
    }

    private fun getMovieDetail(movieID: Int) {

        mainViewModel.getMovieDetail(movieID, queryMap)
        lifecycleScope.launchWhenStarted {
            binding.apply {
                mainViewModel.phoneApiState.collect {
                    when (it) {
                        is ApiState.Data -> {
                            if (it.data != null) {
                                view?.let { it1 ->
                                    Glide.with(it1.context)
                                        .load(Constants.ImageBaseURL + it.data.poster_path)
                                        .into(binding.moviePoster)

                                    binding.movieName.setText(it.data.original_title)


                                    binding.movieRuntime.text = "Vote Count" +  it.data.vote_count  + " Date " + it.data.release_date
                                    binding.moviePlot.setText(it.data.overview)

                                    binding.playTrailer.visibility = View.VISIBLE
                                    binding.movieCastText.visibility = View.VISIBLE
                                    binding.moviePlotText.visibility = View.VISIBLE

                                }
                            }
                            //   binding.movieListItem = it.data
                        }
                        //


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

