package com.example.newscroll.ui.CategoryNews

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.newscroll.Repositories.CrollingRepository
import com.example.newscroll.model.Result
import com.example.newscroll.ui.dashboard.Category
import com.example.newscroll.ui.dashboard.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CategoryNewsViewModel @ViewModelInject constructor(
    private val crollingRepository: CrollingRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _newsList = MutableLiveData<Result<List<News>>>()

    val newsList : LiveData<Result<List<News>>>
        get() = _newsList

    init {
        if (savedStateHandle.contains("url") ) {
            val url = savedStateHandle.get<String>("url")!!
            onGetCategoryNewsInformation(url)
        }
    }

    fun onRefresh() {
        onGetCategoryNewsInformation("https://news.naver.com/main/list.nhn?mode=LS2D&mid=shm&sid1=105&sid2=731")
    }

    private fun onGetCategoryNewsInformation(url : String) {
        viewModelScope.launch {
            _newsList.postValue(Result.Loading(null))
            async(Dispatchers.IO) {
                getNewsList(url)
            }.await()
        }
    }

    suspend fun getNewsList(url : String) {
        _newsList.postValue(crollingRepository.getCategoryNewsList(
            url,
            listOf("dt.photo", "span.lede")
        ))
    }
}