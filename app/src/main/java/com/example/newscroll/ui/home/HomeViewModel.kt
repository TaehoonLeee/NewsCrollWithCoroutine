package com.example.newscroll.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscroll.Repositories.CrollingRepository
import com.example.newscroll.model.Result
import com.example.newscroll.ui.dashboard.Category
import com.example.newscroll.ui.dashboard.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val crollingRepository: CrollingRepository
): ViewModel() {

    private val _newsList = MutableLiveData<Result<List<News>>>()

    val newsList: LiveData<Result<List<News>>>
        get() = _newsList

    init {
        onGetDaumNewsList()
    }

    fun onRefresh() {
        onGetDaumNewsList()
    }

    private fun onGetDaumNewsList() {
        viewModelScope.launch {
            _newsList.postValue(Result.Loading(null))
            async(Dispatchers.IO) {
                getNewsList()
            }.await()
        }
    }

    suspend fun getNewsList() {
        _newsList.postValue(crollingRepository.getDaumNewsList())
    }
}