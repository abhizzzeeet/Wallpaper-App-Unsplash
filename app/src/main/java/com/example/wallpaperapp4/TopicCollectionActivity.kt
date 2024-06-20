package com.example.wallpaperapp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp4.networking.RetrofitObject
import com.example.wallpaperapp4.paging.WallpaperRepository
import com.example.wallpaperapp4.recyclerView.TopicAdapter
import com.example.wallpaperapp4.recyclerView.WallpaperAdapter
import com.example.wallpaperapp4.viewmodels.TopicViewModel
import com.example.wallpaperapp4.viewmodels.TopicViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TopicCollectionActivity : AppCompatActivity() {

//    private val viewModel: TopicViewModel by viewModels{
//        TopicViewModelFactory(WallpaperRepository(RetrofitObject.api))
//    }
//
//    private lateinit var adapter: TopicAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_collection)

        val topicId = intent.getStringExtra("TOPIC_ID")
        val textView: TextView = findViewById<TextView>(R.id.topicId)
        textView.text = topicId

//        adapter = WallpaperAdapter()
//
//        val recyclerView = view.findViewById<RecyclerView>(R.id.wallpaperRecyclerView)
//        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
//        recyclerView.adapter = adapter
//
//        lifecycleScope.launch {
//            viewModel.wallpapers.collectLatest {
//                adapter.submitData(it)
//            }
//        }

    }
}