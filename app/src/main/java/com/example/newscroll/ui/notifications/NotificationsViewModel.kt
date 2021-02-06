package com.example.newscroll.ui.notifications

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.newscroll.Repositories.LikeNewsRepository
import com.example.newscroll.Room.LikeNews

class NotificationsViewModel @ViewModelInject constructor(
        private val likeNewsRepository: LikeNewsRepository
) : ViewModel() {
    val newsList : LiveData<List<LikeNews>> =likeNewsRepository.newsList.asLiveData()
}