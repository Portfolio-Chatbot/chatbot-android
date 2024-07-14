package com.example.basicchatbot.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.basicchatbot.data.response.BibleResponse
import com.example.basicchatbot.data.response.VersesItem
import com.example.basicchatbot.data.retrofit.ApiConfig
import com.example.basicchatbot.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRandomQuote()

        binding.btnOther.setOnClickListener {
            showLoading(true)
            getRandomQuote()
        }
    }

    private fun getRandomQuote() {
        val call = ApiConfig.getApiService().getRandomBibleVerse()

        call.enqueue(object : Callback<BibleResponse> {
            override fun onResponse(call: Call<BibleResponse>, response: Response<BibleResponse>) {
                showLoading(false)
                if (response.isSuccessful) {
                    val bibleResponse = response.body()
                    bibleResponse?.let {
                        val randomVerse = it.verses.random()
                        displayVerse(randomVerse)
                    }
                } else {
                    showLoading(true)
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BibleResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun displayVerse(verse: VersesItem) {
        binding.tvQuote.text = verse.text
        binding.tvVerse.text = verse.bookName + " " + verse.chapter + ":" + verse.verse
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}