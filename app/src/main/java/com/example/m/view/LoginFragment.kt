package com.example.m.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.m.R
import com.example.m.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.tvToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_resgisterFragment)
        }
        binding.btnLogin.setOnClickListener {

            var email = binding.edtEmailLogin.text.toString()
            var password = binding.edtPasswordLogin.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_loginFragment2_to_homeFragment)
                    } else{
                        Toast.makeText(context,it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(context, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show()
            }

        }
    }

}