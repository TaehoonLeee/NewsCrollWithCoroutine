package com.example.newscroll.Repositories

import android.util.Log
import com.example.newscroll.Network.CrollingService
import com.example.newscroll.ui.dashboard.News
import com.example.newscroll.model.Result
import com.example.newscroll.ui.dashboard.Category
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CrollingRepository @Inject constructor(private val crollingService: CrollingService){
    fun getCategory(url : String, query : String) : Result<List<Category>> {
        Log.e("Repository", "test")
        return crollingService.getCategory(url ,query)
    }

    fun getNewsList() : Result<List<News>> {
        return crollingService.getNewsList()
    }

    fun getCategoryNewsList(url : String, query : List<String>) : Result<List<News>> {
        return crollingService.getCategoryNewsList(url ,query)
    }

    fun getDaumNewsList() : Result<List<News>> {
        return crollingService.getDaumNewsList()
    }
}