package com.example.wallpaperapp4.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn

import com.example.wallpaperapp4.models.Wallpaper
import com.example.wallpaperapp4.paging.WallpaperRepository
import kotlinx.coroutines.flow.Flow


class TopicCollectionViewModel(private val repository: WallpaperRepository,private val topicId: String) : ViewModel() {

    val topicPhotos: Flow<PagingData<Wallpaper>> = repository.getTopicPhotos(topicId)
        .cachedIn(viewModelScope)
}
