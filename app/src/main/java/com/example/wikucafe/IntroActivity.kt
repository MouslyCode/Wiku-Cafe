package com.example.wikucafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.wikucafe.databinding.ActivityIntroBinding
import com.example.wikucafe.databinding.ActivityMainBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding:ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupButton.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.loginButton.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java ))
        }
    }
}