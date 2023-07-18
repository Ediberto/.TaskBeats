package com.example.taskbeats_ediberto.data.local
import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
//internal interface TaskDao1 {
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)
    //FAZ A MESMA FUNÇÃO DO SQL
    @Query("Select * from task")
    //ESSA FUNÇÃO VAI TRAZER UMA LISTA DE TAREFAS (task)
    fun getAll(): LiveData<List<Task>>

    //FUNÇÃO UPDATE
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(task: Task)
    //FUNÇÃO DELETE TODAS AS TAREFAS
    @Query("DELETE from task")
    suspend fun deleteAll()
    //FUNÇÃO DELETE POR ID VAI DELETAR TODAS AS TEREFAS
    @Query("DELETE from task WHERE id =:id")
    suspend fun deleteById(id: Int)
}
