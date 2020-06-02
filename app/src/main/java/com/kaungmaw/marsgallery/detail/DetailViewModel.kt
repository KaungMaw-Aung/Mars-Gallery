package com.kaungmaw.marsgallery.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kaungmaw.marsgallery.R
import com.kaungmaw.marsgallery.network.MarsGalleryPropertyPhotos

class DetailViewModel(property: MarsGalleryPropertyPhotos , app: Application): ViewModel() {

    private val _propertyDetail = MutableLiveData<MarsGalleryPropertyPhotos>()
    val propertyDetail : LiveData<MarsGalleryPropertyPhotos>
        get() = _propertyDetail

    init {
        _propertyDetail.value = property
    }

    val detailPropertyText: LiveData<String> = Transformations.map(propertyDetail){
        app.applicationContext.getString(R.string.property_detail , it.rover.name , it.camera.fullName , it.earthDate)
    }

}