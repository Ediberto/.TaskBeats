package com.example.taskbeats_ediberto.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface NewsService {
    //AQUI VAMOS COLOCAR O RESTANTE DA URL
    //QUE É DO RETROFIT
    @GET("news?category=science")
    suspend fun fetchNews(): NewsResponse
}