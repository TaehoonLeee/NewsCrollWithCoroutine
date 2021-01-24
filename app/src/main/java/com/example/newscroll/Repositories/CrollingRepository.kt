package com.example.newscroll.Repositories

import com.example.newscroll.Network.CrollingService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CrollingRepository @Inject constructor(private val crollingService: CrollingService){
    suspend fun getCategory(url : String, query : String) : List<String> {
        return crollingService.getCategory(url ,query)
    }
}