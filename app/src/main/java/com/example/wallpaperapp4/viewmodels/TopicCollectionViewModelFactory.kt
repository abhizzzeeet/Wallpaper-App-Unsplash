package com.example.wallpaperapp4.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallpaperapp4.paging.WallpaperRepository


class TopicCollectionViewModelFactory(private val repository: WallpaperRepository, private val topicId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopicCollectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TopicCollectionViewModel(repository,topicId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}