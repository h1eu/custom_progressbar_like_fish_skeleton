package com.example.custom_dialog_speed_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.custom_dialog_speed_up.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            this.showDialog()
        }
    }
}