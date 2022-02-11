package com.egoriku.blurhash.koin

import com.egoriku.blurhash.data.UnsplashRepository
import com.egoriku.blurhash.screen.DataScreenModel
import com.egoriku.blurhash.screen.encodedecode.UriScreenModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val module = module {
    factory { UnsplashRepository(androidContext()) }

    factory { DataScreenModel(unsplashRepository = get()) }
    factory { UriScreenModel(context = androidContext()) }
}