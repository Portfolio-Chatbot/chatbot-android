package com.example.basicchatbot.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basicchatbot.databinding.ActivityMainBinding

class Homepage : AppCompatActivity() {
    companion object {
        private val TAG = Homepage::class.java.simpleName
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}