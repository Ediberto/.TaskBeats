package com.example.taskbeats_ediberto.data.remote

import com.google.gson.annotations.SerializedName

//ESTA CLASSE VAI FAZER A SERIALIZAÇÃO DA RESPOSTA DO BACKEND
data class NewsResponse(
     //LISTA
     val data: List<NewsDto>
)
data class NewsDto (
     @SerializedName("uuid")
     val id : String,
     @SerializedName("snippet")
     val content : String,
     @SerializedName("image_url")
     val imageUrl : String,
     val title : String
)