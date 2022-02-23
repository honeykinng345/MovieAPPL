package com.learn.degger.movieappmddnl.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.degger.movieappmddnl.model.Movie
import com.learn.degger.movieappmddnl.repository.Repository
import com.learn.degger.movieappmddnl.utilis.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(private val mainRepository: Repository) : ViewModel() {
    private val _phoneApiState: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val phoneApiState: StateFlow<ApiState> = _phoneApiState
    private val topRatedMoviesList = MutableLiveData<List<Movie>>()

    private val disposables = CompositeDisposable()

    fun getTopRatedMoviesList(): MutableLiveData<List<Movie>> {
        return topRatedMoviesList
    }

    fun getPopularMovies(hashMap: HashMap<String,String>) = viewModelScope.launch {
        mainRepository.getPopularMovies(hashMap)
            .onStart {
                _phoneApiState.value = ApiState.Loading
            }.catch { e ->
                _phoneApiState.value = ApiState.Failure(e)
                Log.d("dddd", "getPhone: "+e.message)

            }.collect { data ->
                _phoneApiState.value = data.body()?.let {
                    ApiState.Success(it.results)
                }!!
                Log.d("dddd", "getPhone: "+ (data.body()!!.results.get(1).original_title))
            }
    }
    fun getUpComingsMovies(hashMap: HashMap<String,String>) =
        viewModelScope.launch {
        mainRepository.getUpComingMovies(hashMap)
            .onStart {
                _phoneApiState.value = ApiState.Loading
            }.catch { e ->
                _phoneApiState.value = ApiState.Failure(e)
                Log.d("dddd", "getPhone: "+e.message)

            }.collect { data ->
                _phoneApiState.value = data.body()?.let {
                    ApiState.Success(it.results)
                }!!
                Log.d("dddd", "getPhone: "+ (data.body()!!.results.get(1).original_title))
            }
    }
    fun getMovieDetail(moiveId : Int ,hashMap: HashMap<String,String>) =
        viewModelScope.launch {
            mainRepository.getMovieDetails(moiveId,hashMap)
                .onStart {
                    _phoneApiState.value = ApiState.Loading
                }.catch { e ->
                    _phoneApiState.value = ApiState.Failure(e)
                    Log.d("dddd", "getPhone: "+e.message)

                }.collect { data ->
                    _phoneApiState.value = data.body()?.let {
                        ApiState.Data(it)
                    }!!
                   // Log.d("dddd", "getPhone: "+ (data.body()!!.results.get(1).original_title))
                }
        }
    fun getTopRatedMovies(map: HashMap<String, String>) {
        disposables.add(mainRepository.getTopRated(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> topRatedMoviesList.setValue(result.results)
                Log.d("ddd", "getTopRatedMovies: "+ result.results[0].original_title)}
            ) { error ->
                Log.d("sddd", "getTopRatedMovies: "+error.message)
            }
        )
    }


}






