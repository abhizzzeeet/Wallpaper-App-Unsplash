package com.example.wallpaperapp4.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wallpaperapp4.R
import com.example.wallpaperapp4.networking.RetrofitApi
import com.example.wallpaperapp4.networking.RetrofitObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RandomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_random, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // launching a new coroutine
        GlobalScope.launch {
            val result = RetrofitObject.api.getWallpapers()
            if (result != null)
            // Checking the results
                Log.d("ayush: ","$result")
        }
    }


}