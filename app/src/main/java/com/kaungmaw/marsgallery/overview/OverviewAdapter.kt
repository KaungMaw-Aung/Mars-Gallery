package com.kaungmaw.marsgallery.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaungmaw.marsgallery.databinding.GalleryGridHolderBinding
import com.kaungmaw.marsgallery.network.MarsGalleryPropertyPhotos

class OverviewAdapter : ListAdapter<MarsGalleryPropertyPhotos,OverviewAdapter.OverviewViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewViewHolder {
        return OverviewViewHolder(GalleryGridHolderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: OverviewViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    //ViewHolder part
    class OverviewViewHolder(private val binding: GalleryGridHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MarsGalleryPropertyPhotos){
            binding.marsPhotoProperty = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<MarsGalleryPropertyPhotos>() {
        override fun areItemsTheSame(oldItem: MarsGalleryPropertyPhotos, newItem: MarsGalleryPropertyPhotos): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: MarsGalleryPropertyPhotos, newItem: MarsGalleryPropertyPhotos): Boolean {
            return oldItem.id == newItem.id
        }

    }
}

