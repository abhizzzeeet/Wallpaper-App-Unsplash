package com.example.wallpaperapp4

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.IOException


class DownloadBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        private const val ARG_IMAGE_URL = "image_url"

        fun newInstance(imageUrl: String) = DownloadBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_IMAGE_URL, imageUrl)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_download_bottom_sheet, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUrl = arguments?.getString(ARG_IMAGE_URL)

        view.findViewById<View>(R.id.downloadButton)?.setOnClickListener {
            imageUrl?.let { downloadImage(it) }
        }

        view.findViewById<View>(R.id.setHomeScreenButton)?.setOnClickListener {
            imageUrl?.let { setAsWallpaper(it, isLockScreen = false) }
        }

        view.findViewById<View>(R.id.setLockScreenButton)?.setOnClickListener {
            imageUrl?.let { setAsWallpaper(it, isLockScreen = true) }
        }
    }

    private fun downloadImage(url: String) {
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("Image Download")
            .setDescription("Downloading image...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "downloaded_image.jpg")

        val downloadManager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)

        Toast.makeText(context, "Downloading image...", Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setAsWallpaper(url: String, isLockScreen: Boolean) {
        Thread {
            try {
                val inputStream = java.net.URL(url).openStream()
                val wallpaperManager = WallpaperManager.getInstance(context)
                if (isLockScreen) {
                    wallpaperManager.setStream(inputStream, null, true, WallpaperManager.FLAG_LOCK)
                } else {
                    wallpaperManager.setStream(inputStream,null,true,WallpaperManager.FLAG_SYSTEM)
                }
                activity?.runOnUiThread {
                    Toast.makeText(context, "Wallpaper set successfully", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                activity?.runOnUiThread {
                    Toast.makeText(context, "Failed to set wallpaper", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }
}
