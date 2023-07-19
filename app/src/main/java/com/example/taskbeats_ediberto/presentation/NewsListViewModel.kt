package com.example.taskbeats_ediberto.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskbeats_ediberto.data.remote.NewsDto
import com.example.taskbeats_ediberto.data.remote.NewsService
import com.example.taskbeats_ediberto.data.remote.RetrofitModule
import kotlinx.coroutines.launch

//AGORA VAMOS CONSUMIR UM SERVIÇO DO BACKEND EM UM VIEWMODEL
//VAMOS ESTENDER ESSA CLASSE À VIEWMODEL
class NewsListViewModel(
    //UMA DEPENDENCIA NO NewsService
    private val newsService : NewsService
) : ViewModel() {
    //CRIAR O LIVEDATA multable
    private val _newsListLiveData = MutableLiveData<List<NewsDto>>()
    val newsListLiveData: LiveData<List<NewsDto>> =_newsListLiveData
    //INICIALIZA O VIEWMODEL
    init {
        getNewsList()
    }
    //FAZ A CHAMADA PARA O BACKEND
    private fun getNewsList() {
        viewModelScope.launch{
            try {
                //CHAMAR AS FUNÇÕES SUSPENSAS
                val topNews = newsService.fetchTopNews().data
                val allNews = newsService.fetchAllNews().data
                //ATUALIZA O MUTABLE LIVEDATA
                _newsListLiveData.value = topNews + allNews
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
            }
        }
    }
    companion object {
        fun create(): NewsListViewModel {
            val newsService = RetrofitModule.createNewService()
            return NewsListViewModel(newsService)
        }
    }
}