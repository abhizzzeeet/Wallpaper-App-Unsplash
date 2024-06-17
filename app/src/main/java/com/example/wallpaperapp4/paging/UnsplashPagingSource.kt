package com.example.wallpaperapp4.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wallpaperapp4.models.Wallpaper
import com.example.wallpaperapp4.networking.RetrofitApi

//Create a PagingSource to load data from the API.

class UnsplashPagingSource(
    private val api: RetrofitApi
) : PagingSource<Int, Wallpaper>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wallpaper> {
        val page = params.key ?: 1
        return try {
            val response = api.getWallpapers(page = page, per_page = params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Wallpaper>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}