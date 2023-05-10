package com.example.m.network

import com.example.m.model.PopularMovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular?api_key=10082fe9fdfeefb6c73522a0a8d82266&language=en-US&page=1")
    fun getMoviePopular(): Call<PopularMovieResponse>
}