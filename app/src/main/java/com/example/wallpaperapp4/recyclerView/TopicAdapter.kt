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

import com.squareup.picasso.Picasso

class TopicAdapter(private val listener: OnTopicClickListener) : PagingDataAdapter<Topic, TopicAdapter.TopicViewHolder>(TopicComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_topics, parent, false)
        return TopicViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = getItem(position)
        topic?.let {
            holder.bind(it)
        }
    }

    inner class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val topicTextView: TextView = itemView.findViewById(R.id.topicTextView)
        private val topicImageView: ImageView = itemView.findViewById(R.id.topicImageView)


        fun bind(topic: Topic) {
            val blurHash = topic.cover_photo.blur_hash
            val imageUrl = topic.cover_photo.urls.small
            val title = topic.title
            topicTextView.text=title
            // Decode BlurHash into Bitmap and load image using Picasso
            decodeBlurHashAndLoad(blurHash, imageUrl, topicImageView)
            // Example of using Picasso to load image from URL into ImageView
            itemView.setOnClickListener {
                listener.OnTopicClick(topic)

            }

        }

    }

    object TopicComparator : DiffUtil.ItemCallback<Topic>() {
        override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
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