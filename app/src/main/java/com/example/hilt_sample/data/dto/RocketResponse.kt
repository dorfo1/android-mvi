package com.example.hilt_sample.data.dto

data class RocketResponse(
    val name : String?,
    val flickr_images : List<String>?,
    val description : String?,
    val kg : Int?
)