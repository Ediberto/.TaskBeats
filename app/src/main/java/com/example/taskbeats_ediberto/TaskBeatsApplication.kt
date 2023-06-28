package com.example.taskbeats_ediberto
import android.app.Application
import androidx.room.Room
import com.example.taskbeats_ediberto.data.AppDatabase

class TaskBeatsApplication: Application() {

    //CRIAR A variavel dataBase DE DADOS
    private lateinit var dataBase: AppDatabase
    override fun onCreate() {
        super.onCreate()
        //CRIAR A BASE DE DADOS
        dataBase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "taskbeats-database"
        ).build()
    }
    fun getAppDataBase(): AppDatabase {
        return dataBase
    }
}