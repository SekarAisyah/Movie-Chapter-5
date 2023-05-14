package com.example.m.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m.R
import com.example.m.adapter.MovieAdapter
import com.example.m.databinding.FragmentHomeBinding
import com.example.m.viewmodel.MovieViewModel
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var firebaseAuth:FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.tbHome)

        firebaseAuth = FirebaseAuth.getInstance()
        if(firebaseAuth.currentUser == null){
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment2)
        }

        sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        var getUser = sharedPreferences.getString("user", "")
        binding.textJudul.text = "Welcome, $getUser"

        binding.ivIcprofile.setOnClickListener {
            var addUser = sharedPreferences.edit()
            addUser.putString("user", getUser)
            addUser.apply()
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        val viewModelFilm = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModelFilm.callMovie()
        viewModelFilm.liveDataMovie.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.rvListFilm.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvListFilm.adapter = MovieAdapter(it)
            }
        })

    }

    override fun onStart() {
        super.onStart()

    }
}