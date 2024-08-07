package com.example.wallpaperapp4.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp4.R
import com.example.wallpaperapp4.TopicCollectionActivity
import com.example.wallpaperapp4.models.Topic
import com.example.wallpaperapp4.networking.RetrofitObject
import com.example.wallpaperapp4.paging.WallpaperRepository
import com.example.wallpaperapp4.recyclerView.OnTopicClickListener
import com.example.wallpaperapp4.recyclerView.TopicAdapter

import com.example.wallpaperapp4.viewmodels.TopicViewModel
import com.example.wallpaperapp4.viewmodels.TopicViewModelFactory


import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CategoryFragment : Fragment(), OnTopicClickListener {

    private val viewModel: TopicViewModel by viewModels{
        TopicViewModelFactory(WallpaperRepository(RetrofitObject.api))
    }

    private lateinit var adapter: TopicAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_category, container, false)

        adapter = TopicAdapter(this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.categoryRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        recyclerView.adapter = adapter


        lifecycleScope.launch {
            viewModel.topics.collectLatest {pagingData->
                Log.d("CategoryFragment", "Received paging data: $pagingData")
                adapter.submitData(pagingData)
            }
        }
        return view
    }

    override fun OnTopicClick(topic: Topic) {
        val intent = Intent(requireContext(), TopicCollectionActivity::class.java)
        intent.putExtra("TOPIC_ID", topic.id)
        intent.putExtra("TOPIC_TITLE", topic.title)
        startActivity(intent)
    }



}