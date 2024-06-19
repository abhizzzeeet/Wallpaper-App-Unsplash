package com.example.wallpaperapp4.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp4.R
import com.example.wallpaperapp4.networking.RetrofitApi
import com.example.wallpaperapp4.networking.RetrofitObject
import com.example.wallpaperapp4.paging.WallpaperRepository
import com.example.wallpaperapp4.recyclerView.WallpaperAdapter
import com.example.wallpaperapp4.viewmodels.WallpaperViewModel
import com.example.wallpaperapp4.viewmodels.WallpaperViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RandomFragment : Fragment() {

    private val viewModel: WallpaperViewModel by viewModels {
        WallpaperViewModelFactory(WallpaperRepository(RetrofitObject.api))
    }
    private lateinit var adapter: WallpaperAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_random, container, false)

        adapter = WallpaperAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.wallpaperRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.wallpapers.collectLatest {
                adapter.submitData(it)
            }
        }
        return view
    }




}