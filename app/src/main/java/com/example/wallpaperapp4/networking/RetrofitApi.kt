package com.example.wallpaperapp4.networking

import com.example.wallpaperapp4.models.Topic
import com.example.wallpaperapp4.models.Wallpaper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {
    @GET("/photos")
    suspend fun getWallpapers(
        @Query("client_id") client_id: String = "k8og2spo_YGZ6_mjupQlH8z8FPk-U5omv3RdHskTr34",
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 10
    ) : List<Wallpaper>

    @GET("/topics")
    suspend fun getTopics(
        @Query("client_id") client_id: String = "k8og2spo_YGZ6_mjupQlH8z8FPk-U5omv3RdHskTr34",
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 10
    ) : List<Topic>

    @GET("/topics/{id_or_slug}/photos")
    suspend fun getTopicPhotos(
        @Path("id_or_slug") topic_id: String ,
        @Query("client_id") client_id: String = "k8og2spo_YGZ6_mjupQlH8z8FPk-U5omv3RdHskTr34",
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 10
    ) : List<Wallpaper>
}