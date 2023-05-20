package com.example.taskbeats_ediberto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var titlo : String,
    val descricao : String
): Serializable
