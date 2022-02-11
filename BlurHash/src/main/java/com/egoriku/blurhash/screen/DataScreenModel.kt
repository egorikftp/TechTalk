package com.egoriku.blurhash.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.egoriku.blurhash.data.UnsplashRepository
import com.egoriku.blurhash.data.UnsplashItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DataScreenModel(private val unsplashRepository: UnsplashRepository) : ScreenModel {

    private val stateFlow = MutableStateFlow<List<UnsplashItem>>(emptyList())
    val unsplashState = stateFlow.asStateFlow()

    init {
        coroutineScope.launch {
            stateFlow.value = unsplashRepository.provideDeliveryData()
        }
    }
}