package com.alvaromr.mypunkbeers.data.remote

import com.alvaromr.mypunkbeers.data.remote.api.BeerApiDto
import com.alvaromr.mypunkbeers.domain.model.Beer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerRemoteMapper @Inject constructor() {
    fun toModel(dto: BeerApiDto) = with(dto) {
        Beer(
            id = id,
            name = name,
            subtitle = tagline,
            description = description,
            imageUrl = image_url
        )
    }
}