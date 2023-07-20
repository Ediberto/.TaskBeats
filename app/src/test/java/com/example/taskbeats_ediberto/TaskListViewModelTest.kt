package com.example.taskbeats_ediberto
import com.example.taskbeats_ediberto.data.local.TaskDao
import com.example.taskbeats_ediberto.presentation.TaskListViewModel
import org.mockito.kotlin.mock

class TaskListViewModelTest {
    private val taskDao: TaskDao = mock()
    private val underTest: TaskListViewModel by lazy {
        TaskListViewModel(
            taskDao
        )
    }
}