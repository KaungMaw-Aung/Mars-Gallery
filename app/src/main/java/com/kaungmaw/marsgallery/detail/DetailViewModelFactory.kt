package com.kaungmaw.marsgallery.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kaungmaw.marsgallery.network.MarsGalleryPropertyPhotos

class DetailViewModelFactory(val property: MarsGalleryPropertyPhotos , val application: Application) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(property, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}