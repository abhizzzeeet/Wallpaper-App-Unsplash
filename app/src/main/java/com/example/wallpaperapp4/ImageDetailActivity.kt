package com.example.wallpaperapp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

class ImageDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)

        val imageView: ImageView = findViewById(R.id.imageView)
        val downloadButton: Button = findViewById(R.id.downloadButton)
        val backButton: Button = findViewById(R.id.backButton)

        val imageUrl : String?= intent.getStringExtra("fullImageUrl")
        if (imageUrl == null) {
            Toast.makeText(this, "Image URL is missing", Toast.LENGTH_SHORT).show()
            finish() // Close the activity
            return
        }
        if (imageUrl != null) {
            Picasso.get()
                .load(imageUrl)
                .into(imageView)
        }

        backButton.setOnClickListener {
            finish()
        }

        downloadButton.setOnClickListener {
            // Show bottom sheet with download options
            val downloadBottomSheet = DownloadBottomSheetFragment.newInstance(imageUrl)
            downloadBottomSheet.show(supportFragmentManager, "DownloadBottomSheet")
        }
    }
}
