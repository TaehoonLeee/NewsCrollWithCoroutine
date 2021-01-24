package com.example.newscroll.ui.dashboard

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newscroll.Repositories.CrollingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DashboardViewModel @ViewModelInject constructor(
    private val crollingRepository: CrollingRepository
): ViewModel() {

    var category : List<String> = ArrayList()
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    init {
        CoroutineScope(IO).launch {
            category = crollingRepository.getCategory(
                "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105",
                "ul.nav"
            )
        }
        _text.value = "test"
    }

//    fun getCategory(url : String, query : String) {
//        viewModelScope.launch {
//            category = crollingRepository.getCategory(url, query)
//        }
//    }
}