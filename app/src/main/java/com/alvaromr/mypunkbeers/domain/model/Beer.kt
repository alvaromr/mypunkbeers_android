package com.alvaromr.mypunkbeers.domain.model

data class Beer(
    val id: Int,
    val name: String,
    val subtitle: String,
    val description: String,
    val alcoholByVolume: Double,
    val imageUrl: String,
)