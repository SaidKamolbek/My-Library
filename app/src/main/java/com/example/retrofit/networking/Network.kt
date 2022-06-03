package com.example.retrofit.networking

import android.content.Context

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private const val baseURL = "https://api-football-standings.azharimm.site/"

    fun getRetrofit(context: Context): Retrofit {

//        val chucker = ChuckerInterceptor.Builder(context).build()
//        val client = OkHttpClient.Builder()
//            .addInterceptor(chucker).build()

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
            .build()
    }
}