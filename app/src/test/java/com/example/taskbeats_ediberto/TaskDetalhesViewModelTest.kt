package com.example.taskbeats_ediberto

import com.example.taskbeats_ediberto.data.Task
import com.example.taskbeats_ediberto.data.TaskDao
import com.example.taskbeats_ediberto.presentation.ActionType
import com.example.taskbeats_ediberto.presentation.TaskAction
import com.example.taskbeats_ediberto.presentation.TaskDetalhesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
@OptIn(ExperimentalCoroutinesApi::class)
class TaskDetalhesViewModelTest {
    private val taskDao: TaskDao = mock()
    private val underTest: TaskDetalhesViewModel by lazy {
        TaskDetalhesViewModel(
            taskDao,
            UnconfinedTestDispatcher()
        )
    }
    @Test
    fun update_task() = runTest {
        //Given
        val task = Task(
            id = 1,
            titlo = "Titlo",
            descricao = "Descrição"
        )
        val taskAction = TaskAction(
            task = task,
            actionType = ActionType.UPDATE.name
        )
        //When
        underTest.execute(taskAction)
        //When
        verify(taskDao).update(task)
    }

    @Test
    fun delete_task() = runTest {
        //Given
        val task = Task(
            id = 1,
            titlo = "Titlo",
            descricao = "Descrição"
        )
        val taskAction = TaskAction(
            task = task,
            actionType = ActionType.DELETE.name
        )
        //When
        underTest.execute(taskAction)
        //When
        verify(taskDao).deleteById(task.id)
    }

    @Test
    fun create_task() = runTest {
        //Given
        val task = Task(
            id = 1,
            titlo = "Titlo",
            descricao = "Descrição"
        )
        val taskAction = TaskAction(
            task = task,
            actionType = ActionType.CREATE.name
        )
        //When
        underTest.execute(taskAction)
        //Then
        verify(taskDao).insert(task)
    }
}