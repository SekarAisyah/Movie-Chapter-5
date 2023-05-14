package com.example.m.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.m.R
import com.example.m.databinding.ItemFilmBinding
import com.example.m.model.PopularMovieItem

//mendefinisikan kelas MovieAdapter yang merupakan instance dari RecyclerView.Adapter. Konstruktor dari kelas ini menerima sebuah list dari PopularMovieItem yang akan ditampilkan dalam adapter.
class MovieAdapter(var listFilm: List<PopularMovieItem>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    //ViewHolder adalah sebuah kelas yang merepresentasikan tampilan satu item pada RecyclerView. Setiap item pada RecyclerView memiliki satu ViewHolder,
    // yang bertanggung jawab untuk menampilkan dan mengatur data untuk tampilan item tersebut.

    //mendefinisikan kelas ViewHolder yang digunakan untuk menampung view yang akan ditampilkan pada adapter.
    class ViewHolder(var binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindMovie(itemFilms: PopularMovieItem){
            binding.film = itemFilms
            binding.cardView.setOnClickListener{
                val bundle = Bundle()
                bundle.putSerializable("BUNDEL", itemFilms)
                Navigation.findNavController(itemView).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            }
        }
    }

    //membuat dan menginisialisasi ViewHolder baru untuk menampilkan item pada posisi tertentu di dalam RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    //mendefinisikan fungsi yang digunakan untuk mengatur data pada ViewHolder.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //memanggil fungsi bindMovie pada ViewHolder yang akan mengikat data pada PopularMovieItem dengan view yang ada pada ViewHolder.
        holder.bindMovie(listFilm[position])

        //menggunakan library Glide untuk memuat gambar poster film ke dalam ImageView pada ViewHolder.
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${listFilm[position].posterPath}")
            .into(holder.binding.imgFilm)

        //menambahkan listener pada item view dalam adapter, ketika item view di klik maka akan membuka detail film yang dipilih.
        holder.itemView.setOnClickListener {
            //membuat object Bundle untuk mengirim data antar fragment.
            val bundle = Bundle()

            // baris ini menambahkan data listFilm[position] ke dalam Bundle dengan key BUNDEL.
            bundle.putSerializable("BUNDEL", listFilm[position])

            //baris ini digunakan untuk berpindah ke halaman detail film ketika item view diklik. findNavController() akan mencari NavController yang terkait dengan it dan kemudian akan melakukan navigasi ke fragment detailFragment dengan membawa bundle data.
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
    }

    //mendefinisikan fungsi yang digunakan untuk mengembalikan jumlah item dalam daftar film yang akan ditampilkan dalam adapter.
    override fun getItemCount(): Int {
        return listFilm.size
    }

}