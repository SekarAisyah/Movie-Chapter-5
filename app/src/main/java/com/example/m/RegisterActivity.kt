package com.example.m

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.m.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.tvToLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener{
            val email = binding.etEmailRegister.text.toString()
            val password = binding.edtPasswordRegister.text.toString()

            //Validasi email
            if(email.isEmpty()) {
                binding.etEmailRegister.error = "Email Harus Diisi!"
                binding.etEmailRegister.requestFocus()
                return@setOnClickListener
            }

            //Validasi email tidak sesuai
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmailRegister.error = "Email tidak valid"
                binding.etEmailRegister.requestFocus()
                return@setOnClickListener
            }
            
            //Validasi Password
            if(password.isEmpty()) {
                binding.edtPasswordRegister.error = "Password Harus Diisi!!"
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            
            //Validasi panjang password
            if(password.length<8){
                binding.edtPasswordRegister.error = "Password Minimal 8 Karakter"
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            
            RegisterFirebase(email, password)

        }
    }

    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }
}