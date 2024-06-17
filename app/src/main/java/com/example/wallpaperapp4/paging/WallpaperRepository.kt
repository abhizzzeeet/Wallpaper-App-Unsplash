package com.example.wallpaperapp4.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wallpaperapp4.models.Wallpaper
import com.example.wallpaperapp4.networking.RetrofitApi
import kotlinx.coroutines.flow.Flow


class WallpaperRepository(private val api: RetrofitApi) {

    fun getWallpapers(): Flow<PagingData<Wallpaper>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(api) }
        ).flow
    }
}