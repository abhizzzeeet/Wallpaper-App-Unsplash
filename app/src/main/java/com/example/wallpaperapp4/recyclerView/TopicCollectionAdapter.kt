package com.example.wallpaperapp4.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp4.R
import com.example.wallpaperapp4.blurhashdecoder.BlurHashDecoder
import com.example.wallpaperapp4.models.Topic
import com.example.wallpaperapp4.models.Wallpaper
import com.squareup.picasso.Picasso



class TopicCollectionAdapter(private val onImageClick: (String?) -> Unit) : PagingDataAdapter<Wallpaper, TopicCollectionAdapter.TopicCollectionViewHolder>(TopicCollectionComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicCollectionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_wallpapers, parent, false)
        return TopicCollectionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TopicCollectionViewHolder, position: Int) {
        val wallpaper = getItem(position)
        wallpaper?.let {
            holder.bind(it)
        }

        val fullImageUrl: String? = wallpaper?.urls?.full

        holder.itemView.setOnClickListener{
            onImageClick(fullImageUrl)
        }
    }

    inner class TopicCollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val wallpaperImageView: ImageView = itemView.findViewById(R.id.wallpaperImageView)


        fun bind(wallpaper: Wallpaper) {
            val blurHash = wallpaper.blur_hash
            val imageUrl = wallpaper.urls.small
            // Decode BlurHash into Bitmap and load image using Picasso
            decodeBlurHashAndLoad(blurHash, imageUrl, wallpaperImageView)
            // Example of using Picasso to load image from URL into ImageView


        }

    }

    object TopicCollectionComparator : DiffUtil.ItemCallback<Wallpaper>() {
        override fun areItemsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
            return oldItem == newItem
        }
    }
    private fun decodeBlurHashAndLoad(blurHash: String, imageUrl: String, imageView: ImageView) {
        // Decode BlurHash into Bitmap
        val bitmap = BlurHashDecoder.decode(blurHash, 32, 32)

        // Load image using Picasso
        Picasso.get()
            .load(imageUrl)
//            .placeholder(bitmap) // Placeholder with BlurHash decoded bitmap
            .into(imageView)
    }
}