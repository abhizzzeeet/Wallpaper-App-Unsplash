package com.example.wallpaperapp4.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wallpaperapp4.models.Topic
import com.example.wallpaperapp4.networking.RetrofitApi
import kotlinx.coroutines.flow.Flow

class TopicRepository(private val api: RetrofitApi) {

    fun getTopics(): Flow<PagingData<Topic>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TopicPagingSource(api) }
        ).flow
    }
}