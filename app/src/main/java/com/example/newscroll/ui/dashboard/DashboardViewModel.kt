package com.example.newscroll.ui.dashboard

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscroll.Repositories.CrollingRepository
import com.example.newscroll.Repositories.LikeNewsRepository
import com.example.newscroll.Room.LikeNews
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

import com.example.newscroll.model.Result
import kotlinx.coroutines.async

class DashboardViewModel @ViewModelInject constructor(
    private val crollingRepository: CrollingRepository,
    private val likeNewsRepository: LikeNewsRepository
): ViewModel() {

    private val _category = MutableLiveData<Result<List<Category>>>()
    private val _newsList = MutableLiveData<Result<List<News>>>()

    val category : LiveData<Result<List<Category>>>
        get() = _category
    val newsList : LiveData<Result<List<News>>>
        get() = _newsList

    init {
        onGetNaverNewsInformation()
    }

    fun onRefresh() {
        onGetNaverNewsInformation()
    }

    private fun onGetNaverNewsInformation() {
        viewModelScope.launch {
            _category.postValue(Result.Loading(null))
            _newsList.postValue(Result.Loading(null))
            async(IO) {
                getCategory(
                    "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105",
                    "ul.nav a"
                )
                getNewsList()
            }.await()
        }
    }

    suspend fun getCategory(url : String, query : String) {
        _category.postValue(crollingRepository.getCategory(
            url,
            query
        ))
    }

    suspend fun getNewsList() {
        _newsList.postValue(crollingRepository.getNewsList())
    }

    fun insert(news : LikeNews) = viewModelScope.launch {
        async(IO) { likeNewsRepository.insert(news) }.await()
    }
}