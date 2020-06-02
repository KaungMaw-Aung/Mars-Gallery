package com.kaungmaw.marsgallery.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class MarsGalleryProperty(
    @Json(name = "photos") val photos: List<MarsGalleryPropertyPhotos>
)


@Parcelize
@JsonClass(generateAdapter = true)
data class MarsGalleryPropertyPhotos(
    @Json(name = "id") val id: Long,
    @Json(name = "camera") val camera: MarsGalleryPropertyCamera,
    @Json(name = "img_src") val marsImgUrl: String,
    @Json(name = "earth_date") val earthDate: String,
    @Json(name = "rover") val rover: MarsGalleryPropertyRover
):Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class MarsGalleryPropertyCamera(
    @Json(name = "full_name") val fullName: String
):Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class MarsGalleryPropertyRover(
    @Json(name = "name") val name: String
):Parcelable