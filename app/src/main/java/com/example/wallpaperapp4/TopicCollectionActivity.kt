package com.example.wallpaperapp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

import androidx.activity.viewModels

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp4.networking.RetrofitObject
import com.example.wallpaperapp4.paging.WallpaperRepository

import com.example.wallpaperapp4.recyclerView.TopicCollectionAdapter

import com.example.wallpaperapp4.viewmodels.TopicCollectionViewModel
import com.example.wallpaperapp4.viewmodels.TopicCollectionViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TopicCollectionActivity : AppCompatActivity() {



    private lateinit var adapter: TopicCollectionAdapter
    private lateinit var topicTitleTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_collection)

        val topicId : String = intent.getStringExtra("TOPIC_ID") ?: ""
        val topicTitle : String = intent.getStringExtra("TOPIC_TITLE") ?: ""

        topicTitleTextView = findViewById(R.id.topic_title)
        topicTitleTextView.text = topicTitle
        // launching a new coroutine
//        GlobalScope.launch {
//            val result = RetrofitObject.api.getTopicPhotos( topicId, "k8og2spo_YGZ6_mjupQlH8z8FPk-U5omv3RdHskTr34", 1,10)
//            if (result != null)
//            // Checking the results
//                Log.d("ayush: ", "$result")
//        }

        val viewModel: TopicCollectionViewModel by viewModels{
            TopicCollectionViewModelFactory(WallpaperRepository(RetrofitObject.api) , topicId)
        }

        adapter = TopicCollectionAdapter{ fullImageUrl ->
            val intent = Intent(this, ImageDetailActivity::class.java).apply{
                putExtra("fullImageUrl", fullImageUrl)
            }
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.topicCollectionRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.topicPhotos.collectLatest {
                adapter.submitData(it)
            }
        }

    }
}