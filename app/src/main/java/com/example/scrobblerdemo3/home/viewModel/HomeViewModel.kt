package com.example.scrobblerdemo3.home.viewModel

import androidx.lifecycle.ViewModel
import com.example.scrobblerdemo3.home.model.HomeBaseEntity
import com.example.scrobblerdemo3.common.base.BaseUiState
import kotlinx.coroutines.flow.StateFlow

abstract class HomeViewModel : ViewModel() {
    abstract val dataState: StateFlow<BaseUiState<List<HomeBaseEntity.HomeEntity>>>

    abstract fun getData()
}