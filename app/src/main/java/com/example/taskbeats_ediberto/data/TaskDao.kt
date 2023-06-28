package com.example.taskbeats_ediberto.data
import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
//internal interface TaskDao1 {
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)
    //FAZ A MESMA FUNÇÃO DO SQL
    @Query("Select * from task")
    //ESSA FUNÇÃO VAI TRAZER UMA LISTA DE TAREFAS (task)
    fun getAll(): LiveData<List<Task>>

    //FUNÇÃO UPDATE
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)
    //FUNÇÃO DELETE TODAS AS TAREFAS
    @Query("DELETE from task")
    fun deleteAll()
    //FUNÇÃO DELETE POR ID VAI DELETAR TODAS AS TEREFAS
    @Query("DELETE from task WHERE id =:id")
    fun deleteById(id: Int)
}
