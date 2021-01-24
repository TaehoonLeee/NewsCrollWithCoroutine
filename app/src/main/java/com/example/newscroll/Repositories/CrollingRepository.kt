package com.example.newscroll.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newscroll.Network.CrollingService
import com.example.newscroll.ui.dashboard.News
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CrollingRepository @Inject constructor(private val crollingService: CrollingService){
    fun getCategory(url : String, query : String) : List<String> {
        Log.e("Repository", "test")
        return crollingService.getCategory(url ,query)
    }

    fun getNewsList() : List<News> {
        return crollingService.getNewsList()
    }
}