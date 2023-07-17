package com.example.taskbeats_ediberto.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.taskbeats_ediberto.TaskBeatsApplication
import com.example.taskbeats_ediberto.data.Task
import com.example.taskbeats_ediberto.data.TaskDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskDetalhesViewModel (
       private val taskDao: TaskDao,
       private val dispatcher: CoroutineDispatcher=Dispatchers.IO
     ): ViewModel() {
    fun execute(taskAction: TaskAction) {
        when (taskAction.actionType) {
            ActionType.DELETE.name -> deleteById(taskAction.task!!.id)
            ActionType.CREATE.name -> insertIntoDataBase(taskAction.task!!)
            ActionType.UPDATE.name -> updateIntoDataBase(taskAction.task!!)
       }
    }
    //DELETA A TAREFA POR ID
    private fun deleteById(id: Int) {
        viewModelScope.launch {
            taskDao.deleteById(id)
        }
    }
    private fun insertIntoDataBase(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }
    private fun updateIntoDataBase(task: Task) {
        viewModelScope.launch {
            taskDao.update(task)
        }
    }
    companion object {
        fun getVMFactory(application: Application) : ViewModelProvider.Factory {
            val dataBaseInstance = (application as TaskBeatsApplication).getAppDataBase()
            val dao = dataBaseInstance.taskDao()
            val factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TaskDetalhesViewModel(dao) as T
                }
            }
            return factory
        }
    }
}