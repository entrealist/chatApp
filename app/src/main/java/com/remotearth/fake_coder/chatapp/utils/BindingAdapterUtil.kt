package com.remotearth.fake_coder.chatapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.remotearth.fake_coder.chatapp.R

object BindingAdapterUtil {

    @BindingAdapter("profileImage")
    @JvmStatic
    fun profileImageAttach(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_select_image)
            .into(imageView)
    }
}