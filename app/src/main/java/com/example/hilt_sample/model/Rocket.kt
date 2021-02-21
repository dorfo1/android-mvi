package com.example.hilt_sample.model

import android.os.Parcelable
import com.example.hilt_sample.data.dto.RocketResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rocket(
    val name : String,
    val photos : List<String>,
    val description : String,
    val kg : Int
) : Parcelable {
    companion object {
        fun fromResponse(response: RocketResponse) : Rocket{
            return Rocket(
                name = response.name ?: "",
                photos = response.flickr_images ?: listOf(),
                description = response.description ?: "",
                kg = response.kg ?: 0
            )
        }
    }
}