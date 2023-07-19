package com.example.taskbeats_ediberto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.taskbeats_ediberto.data.remote.NewsDto
import com.example.taskbeats_ediberto.data.remote.NewsResponse
import com.example.taskbeats_ediberto.data.remote.NewsService
import com.example.taskbeats_ediberto.presentation.NewsListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class NewsListViewModelTest {
    //val topResponse = NewsResponse(data = expectedTop)
    //val allResponse = NewsResponse(data = expectedAll)
    //VAI TIRAR DO DISPATCHER
    @get: Rule
    val mainDispatcherRule = MainDispatcherRule()
    //VAI TIRAR DO LiveData
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    //VAMOS FAZER UM MOCK DO SERVICE
    private val service: NewsService = mock()
    //VAMOS TESTAR A CLASS "NewsListViewModel"
    private lateinit var underTest: NewsListViewModel
    //VAMOS TESTAR O GIVEN E O THEN
    @Test
    fun `GIVEN request succeed news WHEN fetch THEN return list`() {
        runBlocking {
            //VAMOS CRIAR UMA LISTA FAKE
            //GIVEN
            val expectedTop = listOf(
                NewsDto(
                    id = "id1",
                    content = "content1",
                    imageUrl = "image1",
                    title = "title1"
                )
            )
            val expectedAll = listOf(
                NewsDto(
                    id = "id1",
                    content = "content1",
                    imageUrl = "image1",
                    title = "title1"
                )
            )
            //MOCK DA RESPOSTA
           // val response = NewsResponse(data = expected)
            val topResponse = NewsResponse(data = expectedTop)
            val allResponse = NewsResponse(data = expectedAll)
           // val response = NewsResponse(data = expected, category = "tech")

            //VAMOS CHAMAR O underTest
            whenever(service.fetchTopNews()).thenReturn(topResponse)
            whenever(service.fetchAllNews()).thenReturn(allResponse)
            //WHEN
            underTest = NewsListViewModel(service)
            //RESULTADO FINAL
            //THEN
            val result = underTest.newsListLiveData.getOrAwaitValue()
            assert(result == expectedTop + expectedAll)
        }
    }
}