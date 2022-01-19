package com.egoriku.blurhash.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.egoriku.blurhash.data.MenuRepository
import com.egoriku.blurhash.data.MenuItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DataScreenModel(private val menuRepository: MenuRepository) : ScreenModel {

    private val stateFlow = MutableStateFlow<List<MenuItem>>(emptyList())
    val delivery = stateFlow.asStateFlow()

    init {
        coroutineScope.launch {
            stateFlow.value = menuRepository.provideDeliveryData()
        }
    }
}