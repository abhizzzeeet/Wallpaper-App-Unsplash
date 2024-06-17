package com.example.wallpaperapp4.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallpaperapp4.models.Wallpaper
import com.example.wallpaperapp4.paging.WallpaperRepository
import kotlinx.coroutines.flow.Flow


class WallpaperViewModel(private val repository: WallpaperRepository) : ViewModel() {

    val wallpapers: Flow<PagingData<Wallpaper>> = repository.getWallpapers()
        .cachedIn(viewModelScope)
}

