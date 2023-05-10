package com.example.m.viewmodel

import com.example.m.model.PopularMovieItem
import com.example.m.model.PopularMovieResponse
import com.example.m.network.ApiClient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    var liveDataMovie: MutableLiveData<List<PopularMovieItem>?> = MutableLiveData()

    fun callMovie() {
        ApiClient.instance.getMoviePopular().enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if (response.isSuccessful) {
                    liveDataMovie.postValue(response.body()?.results)
                } else {
                    liveDataMovie.postValue(null)
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                liveDataMovie.postValue(null)
            }
        })
    }
}
