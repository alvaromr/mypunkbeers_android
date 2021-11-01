package com.alvaromr.mypunkbeers.data.remote.api

import kotlinx.serialization.Serializable

@Serializable
data class BeerApiDto(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val abv: Double,
    val image_url: String = "",
)