package com.example.wallpaperapp4.models

data class Topic(
    val id: String,
    val title: String,
    val description: String,
    val total_photos: String,
    val cover_photo: coverPhoto
)

data class coverPhoto(
    val id: String,
    val blur_hash : String,
    val urls: UrlCoverPhoto
)

data class UrlCoverPhoto(
    val full: String,
    val small : String
)
