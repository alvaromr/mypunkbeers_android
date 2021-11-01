package com.alvaromr.mypunkbeers

import com.alvaromr.mypunkbeers.domain.model.Beer

fun fakeBeerBuilder(i: Int) =
    Beer(
        id = i,
        name = "$i-name",
        subtitle = "$i-subtitle",
        description = "$i-description",
        alcoholByVolume = i.toDouble(),
        imageUrl = "$i-url"
    )

val fakeBeers = Array(25) { i -> fakeBeerBuilder(i) }.toList()
val fakeBeer = fakeBeerBuilder(1)