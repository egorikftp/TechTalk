package com.egoriku.blurhash.koin

import com.egoriku.blurhash.data.MenuRepository
import com.egoriku.blurhash.screen.DataScreenModel
import com.egoriku.blurhash.screen.encodedecode.UriScreenModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val module = module {
    factory { MenuRepository(androidContext()) }

    factory { DataScreenModel(menuRepository = get()) }
    factory { UriScreenModel(context = androidContext()) }
}