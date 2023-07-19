package com.example.taskbeats_ediberto.data.remote

import com.example.taskbeats_ediberto.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {

    fun createNewService(): NewsService{
        val logging = HttpLoggingInterceptor()
        logging.apply {
            HttpLoggingInterceptor.Level.BODY
        }
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val retrofit = Retrofit
            .Builder()
            .client(client)
            .baseUrl("https://api.thenewsapi.com/v1/news/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
        return retrofit
            .build()
            .create(NewsService::class.java)
    }
}