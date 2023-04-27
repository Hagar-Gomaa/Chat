package com.example.chat

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageid")
fun bintImageView(image: ImageView, id: Int) {
    image.setImageResource(id)
}