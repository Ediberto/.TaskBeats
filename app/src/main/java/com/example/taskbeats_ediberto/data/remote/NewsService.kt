package com.example.taskbeats_ediberto.data.remote

import com.example.taskbeats_ediberto.BuildConfig
import retrofit2.http.GET

interface NewsService {
    //AQUI VAMOS COLOCAR O RESTANTE DA URL
    //QUE É DO RETROFIT
    @GET("top?api_token=${BuildConfig.API_KEY}&locale=us")
    suspend fun fetchTopNews(): NewsResponse

    @GET("all?api_token=${BuildConfig.API_KEY}")
    suspend fun fetchAllNews(): NewsResponse
}