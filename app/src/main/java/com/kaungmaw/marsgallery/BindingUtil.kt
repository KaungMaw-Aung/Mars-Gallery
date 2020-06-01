package com.kaungmaw.marsgallery

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kaungmaw.marsgallery.network.MarsGalleryPropertyPhotos
import com.kaungmaw.marsgallery.overview.NetworkStatus
import com.kaungmaw.marsgallery.overview.OverviewAdapter

@BindingAdapter("loadImgUrl")
fun bindImg(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imgUri)
            .apply(RequestOptions.placeholderOf(R.drawable.loading_animation).error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}

@BindingAdapter("loadStatusImg")
fun bindStatusImg(imageView: ImageView, statusValue: NetworkStatus?) {
    statusValue?.let {
        when (it) {
            NetworkStatus.LOADING -> {
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.loading_animation)
            }
            NetworkStatus.ERROR -> {
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.ic_connection_error)
            }
            else -> imageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("bindList")
fun bindList(recyclerView: RecyclerView, data: List<MarsGalleryPropertyPhotos>?){
    data?.let {
        val adapter = recyclerView.adapter as OverviewAdapter
        adapter.submitList(data)
    }
}
