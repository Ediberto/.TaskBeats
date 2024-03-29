package com.example.taskbeats_ediberto

import com.example.taskbeats_ediberto.data.local.Task
import com.example.taskbeats_ediberto.data.local.TaskDao
import com.example.taskbeats_ediberto.presentation.ActionType
import com.example.taskbeats_ediberto.presentation.TaskAction
import com.example.taskbeats_ediberto.presentation.TaskDetalhesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
@OptIn(ExperimentalCoroutinesApi::class)
class TaskDetalhesViewModelTest {

    //CRIAR UMA NOVA INSTANCIA DA "MainDispatcherRule.kt"
    @get:Rule //EXECUTADOR DE TESTE
    //CRIAR UMA NOVA INSTANCIA DA "MainDispatcherRule.kt"

    val mainDispatcherRule = MainDispatcherRule()
    private val taskDao: TaskDao = mock()

    private val underTest: TaskDetalhesViewModel by lazy {
        TaskDetalhesViewModel(
            taskDao
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