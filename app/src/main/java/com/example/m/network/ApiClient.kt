package com.example.m.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Kode dibawah ini adalah implementasi kelas ApiClient yang bertanggung jawab untuk mengakses REST API dengan menggunakan Retrofit dan OkHttp pada aplikasi Android.
object ApiClient {

    //BASE_URL adalah konstanta yang menyimpan alamat dasar URL dari REST API
    const val BASE_URL = "https://api.themoviedb.org/3/"

    //logging adalah sebuah property yang berfungsi untuk menampilkan log request dan response pada konsol.
    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

    //client adalah sebuah objek OkHttpClient yang diinisialisasi dengan logging sebagai intersepctor untuk memproses request dan response dari REST API.
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()

    //instance adalah property yang membangun objek Retrofit dan mengonfigurasinya untuk mengirim request ke URL yang ditentukan, dan kemudian memanggil method create() untuk membuat implementasi API yang akan digunakan.
    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(ApiService::class.java)
    }

    //Pada implementasi instance, objek Retrofit diinisialisasi dengan BASE_URL, kemudian ditambahkan converter untuk mengubah respon JSON ke objek Kotlin, dan diakhiri dengan memanggil client untuk digunakan dalam mengirim request ke REST API.
}