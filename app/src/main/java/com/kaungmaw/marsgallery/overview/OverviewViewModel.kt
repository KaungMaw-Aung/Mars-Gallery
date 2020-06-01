package com.kaungmaw.marsgallery.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaungmaw.marsgallery.network.CameraFilter
import com.kaungmaw.marsgallery.network.MarsApi
import com.kaungmaw.marsgallery.network.MarsGalleryPropertyPhotos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

enum class NetworkStatus{LOADING,ERROR,DONE}
class OverviewViewModel: ViewModel() {

    private val viewModelJob = Job()
    private val scope = CoroutineScope( viewModelJob + Dispatchers.Main )

    private val _property = MutableLiveData<List<MarsGalleryPropertyPhotos>>()
    val property : LiveData<List<MarsGalleryPropertyPhotos>>
        get() = _property

    fun executeFilterCamera(filter: CameraFilter){
        getMarsGalleryProperties(filter)
    }

    init {
        getMarsGalleryProperties(CameraFilter.ALL)
    }

    private fun getMarsGalleryProperties(filter: CameraFilter){
        scope.launch {
            val deferred = MarsApi.retrofitService.getPhotosProperties(1000, filter.value, "0ri3QZ6lzn3OgPvKOeSPT197zqmhnY0fiPOeunlA")
            try {
                _status.value = NetworkStatus.LOADING
                val response = deferred.await()
                _status.value = NetworkStatus.DONE
                _property.value = response.photos
            }catch (e: Exception){
                _status.value = NetworkStatus.ERROR
                _property.value = ArrayList()
                Log.e("OverviewViewModel", "Newwork error : ${e.message!!}")
            }
        }
    }

    //for network status
    private val _status = MutableLiveData<NetworkStatus>()
    val status: LiveData<NetworkStatus>
        get() = _status
}