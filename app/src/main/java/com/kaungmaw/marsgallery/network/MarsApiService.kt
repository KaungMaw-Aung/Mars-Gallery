package com.kaungmaw.marsgallery.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY

private const val BASE_URL = "https://api.nasa.gov/"

enum class CameraFilter(val value: String?){ALL(null) , FHAZ("fhaz") , MAST("mast") , NAVCAM("navcam")}

val moshi = Moshi.Builder().build()

val retrofit =
    Retrofit.Builder().addCallAdapterFactory(CoroutineCallAdapterFactory()).addConverterFactory(
        MoshiConverterFactory.create(moshi)
    ).baseUrl(BASE_URL).build()

interface MarsApiService {
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getPhotosProperties(@Query(value = "sol") sol: Int, @Query(value = "camera") camera: String?, @Query(value = "api_key") apiKey: String): Deferred<MarsGalleryProperty>
}

object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}
