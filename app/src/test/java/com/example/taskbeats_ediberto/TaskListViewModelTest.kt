package com.example.taskbeats_ediberto

import com.example.taskbeats_ediberto.data.Task
import com.example.taskbeats_ediberto.data.TaskDao
import com.example.taskbeats_ediberto.presentation.ActionType
import com.example.taskbeats_ediberto.presentation.TaskAction
import com.example.taskbeats_ediberto.presentation.TaskListViewModel
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class TaskListViewModelTest {
    private val taskDao: TaskDao = mock()
    private val underTest: TaskListViewModel by lazy {
        TaskListViewModel(
            taskDao,
            UnconfinedTestDispatcher()
        )
    }
    @Test
    fun delete_all() = runTest{
        //Given
        val taskAction = TaskAction(
            task = null,
            actionType = ActionType.DELETE_ALL.name
        )
        //When
        underTest.execute(taskAction)
        //When
        verify(taskDao).deleteAll()
    }
    @Test
    fun update_task() = runTest{
        //Given
        val task = Task (
            id = 1,
            titlo = "Titlo",
            descricao = "Descrição"
        )
        val taskAction = TaskAction (
            task = task,
            actionType = ActionType.UPDATE.name
        )
        //When
        underTest.execute(taskAction)
        //When
        verify(taskDao).update(task)
    }
    @Test
    fun delete_task() = runTest{
        //Given
        val task = Task (
            id = 1,
            titlo = "Titlo",
            descricao = "Descrição"
        )
        val taskAction = TaskAction (
            task = task,
            actionType = ActionType.DELETE.name
        )
        //When
        underTest.execute(taskAction)
        //When
        verify(taskDao).deleteById(task.id)
    }
}