package com.example.wallpaperapp4.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp4.R
import com.example.wallpaperapp4.models.Wallpaper
import com.squareup.picasso.Picasso


class WallpaperAdapter : PagingDataAdapter<Wallpaper, WallpaperAdapter.WallpaperViewHolder>(WallpaperComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_wallpapers, parent, false)
        return WallpaperViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val wallpaper = getItem(position)
        wallpaper?.let {
            holder.bind(it)
        }
    }

    class WallpaperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val wallpaperImageView: ImageView = itemView.findViewById(R.id.wallpaperImageView)

        fun bind(wallpaper: Wallpaper) {
            // Example of using Picasso to load image from URL into ImageView
            Picasso.get()
                .load(wallpaper.urls.small)  // Load the small size image URL
                .into(wallpaperImageView)
        }
    }

    object WallpaperComparator : DiffUtil.ItemCallback<Wallpaper>() {
        override fun areItemsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
            return oldItem == newItem
        }
    }
}