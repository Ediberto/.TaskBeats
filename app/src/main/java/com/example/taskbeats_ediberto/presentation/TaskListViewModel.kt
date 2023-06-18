package com.example.taskbeats_ediberto.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskbeats_ediberto.TaskBeatsApplication
import com.example.taskbeats_ediberto.data.Task
import com.example.taskbeats_ediberto.data.TaskDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val taskDao: TaskDao,
    private val dispatcher: CoroutineDispatcher=Dispatchers.IO
) : ViewModel() {
    //AQUI TEREMOS O LIVEDATE NO VIEWMODEL
    val taskListLiveData: LiveData<List<Task>> = taskDao.getAll()
    fun execute(taskAction: TaskAction) {
        when (taskAction.actionType) {
            ActionType.DELETE.name -> deleteById(taskAction.task!!.id)
            ActionType.CREATE.name -> insertIntoDataBase(taskAction.task!!)
            ActionType.UPDATE.name -> updateIntoDataBase(taskAction.task!!)
            ActionType.DELETE_ALL.name -> deleteAll()
        }
    }
    //DELETA A TAREFA POR ID
    private fun deleteById(id: Int) {
        viewModelScope.launch(dispatcher) {
            taskDao.deleteById(id)
        }
    }
    private fun insertIntoDataBase(task: Task) {
        viewModelScope.launch(dispatcher) {
            taskDao.insert(task)
        }
    }
    private fun updateIntoDataBase(task: Task) {
        viewModelScope.launch(dispatcher) {
            taskDao.update(task)
        }
    }
    //DELETA TODAS TAREFAS
    private fun deleteAll() {
        viewModelScope.launch(dispatcher) {
            taskDao.deleteAll()
        }
    }
    //PARA CRIARMOS UM DAO PRECISAMOS CRIAR UM PARAMETRO DE CONSTRUÇÃO
    companion object {
        fun create(application: Application): TaskListViewModel {
            val dataBaseInstance = (application as TaskBeatsApplication).getAppDataBase()
            val dao = dataBaseInstance.taskDao()
            return TaskListViewModel(dao)
        }
    }
}