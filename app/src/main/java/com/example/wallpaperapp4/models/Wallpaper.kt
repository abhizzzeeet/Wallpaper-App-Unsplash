package com.example.wallpaperapp4.models

data class Wallpaper(
    val id: String,
    val blur_hash: String,
    val urls: Url
)

data class Url( 
    val raw: String,
    val full: String,
    val small: String
)
