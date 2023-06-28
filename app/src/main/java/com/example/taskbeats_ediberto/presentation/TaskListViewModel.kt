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

class TaskListViewModel(taskDao: TaskDao): ViewModel() {

    //AQUI TEREMOS O LIVEDATE NO VIEWMODEL
    val taskListLiveData: LiveData<List<Task>> = taskDao.getAll()

    //PARA CRIARMOS UM DAO PRECISAMOS CRIAR UM PARAMETRO DE CONSTRUÇÃO
    companion object {
        fun create(application: Application): TaskListViewModel {
            val dataBaseInstance = (application as TaskBeatsApplication).getAppDataBase()
            val dao = dataBaseInstance.taskDao()
            return TaskListViewModel(dao)
        }
    }
}