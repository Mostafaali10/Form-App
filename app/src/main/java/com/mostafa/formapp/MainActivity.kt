package com.mostafa.formapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mostafa.formapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.submitBtn.setOnClickListener {

            binding.progress.isVisible = true
            val name = binding.nameEt.text.toString()
            val email = binding.emailEt.text.toString()
            val phone = binding.phoneEt.text.toString()
            val linkdin = binding.linkedinEt.text.toString()
            val githup = binding.githubEt.text.toString()
            val u = User(name, email, phone, linkdin, githup)

            db
                .collection("users")
                .add(u)
                .addOnSuccessListener {
                     it.update("id" , it.id)
                    Toast.makeText(this, "Added!", Toast.LENGTH_SHORT).show()
                    binding.progress.isVisible = false
                }


        }




    }
}