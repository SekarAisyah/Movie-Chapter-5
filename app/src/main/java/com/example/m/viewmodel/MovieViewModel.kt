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

    //Membuat sebuah variabel liveDataMovie dengan tipe MutableLiveData<List<PopularMovieItem>?> yang nantinya akan berisi data film populer.
    var liveDataMovie: MutableLiveData<List<PopularMovieItem>?> = MutableLiveData()

    // Membuat fungsi callMovie yang akan memanggil endpoint untuk mendapatkan data film populer dari server.
    fun callMovie() {
        //Menggunakan ApiClient untuk melakukan koneksi ke server dan memanggil method getMoviePopular().
        //Menggunakan enqueue() untuk menjalankan permintaan secara asynchronous dan memberikan Callback untuk menangani hasil respon.
        ApiClient.instance.getMoviePopular().enqueue(object : Callback<PopularMovieResponse> {
            //onResponse() akan dipanggil ketika respon dari server diterima dengan berhasil, dan akan memperbarui nilai liveDataMovie dengan hasil response dari server.
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

            //onFailure() akan dipanggil ketika permintaan gagal karena alasan seperti jaringan atau kesalahan server, dan akan memperbarui nilai liveDataMovie menjadi null
            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                liveDataMovie.postValue(null)
            }
        })
    }
}
