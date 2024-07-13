package com.example.basicchatbot.data.retrofit
import com.example.basicchatbot.data.response.BibleResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("?random=verse")
    fun getRandomBibleVerse(): Call<BibleResponse>
}