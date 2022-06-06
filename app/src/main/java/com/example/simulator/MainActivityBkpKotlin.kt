package com.example.simulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simulator.databinding.ActivityMainBinding

class MainActivityBkpKotlin : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)


        binding.tvHello.setText("teste")
    }
}