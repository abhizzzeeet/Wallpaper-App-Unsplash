package com.example.wallpaperapp4.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallpaperapp4.models.Topic
import com.example.wallpaperapp4.paging.WallpaperRepository

import kotlinx.coroutines.flow.Flow


class TopicViewModel(private val repository: WallpaperRepository) : ViewModel() {

    val topics: Flow<PagingData<Topic>> = repository.getTopics()
        .cachedIn(viewModelScope)
}