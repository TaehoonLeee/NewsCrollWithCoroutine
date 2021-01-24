package com.example.newscroll.ui.dashboard

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscroll.Repositories.CrollingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DashboardViewModel @ViewModelInject constructor(
    private val crollingRepository: CrollingRepository
): ViewModel() {

    private val _category = MutableLiveData<List<String>>()
    private val _newsList = MutableLiveData<List<News>>()

    val category : LiveData<List<String>>
        get() = _category
    val newsList : LiveData<List<News>>
        get() = _newsList

    init {
        viewModelScope.launch {
            CoroutineScope(IO).launch {
                Log.e("viewmodel", "test")
                getCategory("https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105", "ul.nav")
                getNewsList()
            }
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
}