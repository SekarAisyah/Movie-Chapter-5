package com.example.m.network

import com.example.m.model.PopularMovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular?api_key=315cd48d400d88e273e8050a32df1873&language=en-US&page=1")
    // "getMoviePopular()" adalah method yang mengembalikan objek Call<PopularMovieResponse>. Method ini digunakan untuk memanggil endpoint "movie/popular" pada REST API dan mengembalikan response dalam bentuk objek Call.
    // "PopularMovieResponse" adalah sebuah class model yang merepresentasikan struktur data JSON yang diterima dari endpoint "movie/popular". Kelas tersebut digunakan untuk mengonversi response JSON menjadi objek Kotlin.
    fun getMoviePopular(): Call<PopularMovieResponse>
}