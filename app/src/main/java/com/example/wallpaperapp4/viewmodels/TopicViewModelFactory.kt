package com.example.wallpaperapp4.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallpaperapp4.paging.WallpaperRepository


class TopicViewModelFactory(private val repository: WallpaperRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopicViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TopicViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}