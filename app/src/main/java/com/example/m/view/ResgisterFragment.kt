package com.example.m.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.m.R
import com.example.m.databinding.FragmentResgisterBinding
import com.google.firebase.auth.FirebaseAuth

class ResgisterFragment : Fragment() {

    lateinit var binding: FragmentResgisterBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var sharedRegis: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResgisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedRegis = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            var getUsername = binding.edtUsernameRegister.text.toString()
            var getEmail = binding.edtEmailRegister.text.toString()
            var getPassword = binding.edtPasswordRegister.text.toString()
            var getUlangPass = binding.edtUlangiPassword.text.toString()

            var addUser = sharedRegis.edit()
            addUser.putString("user", getUsername)

            if (getUsername.isNotEmpty()&& getEmail.isNotEmpty() && getPassword.isNotEmpty() && getUlangPass.isNotEmpty()){
                if (getPassword == getUlangPass){
                    addUser.apply()
                    firebaseAuth.createUserWithEmailAndPassword(getEmail, getPassword).addOnCompleteListener{
                        if (it.isSuccessful){
                            Toast.makeText(context, "Register Berhasil", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_resgisterFragment_to_loginFragment2)
                        } else{
                            Toast.makeText(context,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(context, "Password Tidak Sama", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, "Data Belum Lengkap", Toast.LENGTH_SHORT).show()
            }

        }
    }
}