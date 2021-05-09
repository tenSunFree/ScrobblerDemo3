package com.example.scrobblerdemo3.home.viewModel

import androidx.lifecycle.viewModelScope
import com.example.scrobblerdemo3.home.model.HomeRepository
import com.example.scrobblerdemo3.home.model.HomeBaseEntity
import com.example.scrobblerdemo3.common.base.BaseUiState
import com.example.scrobblerdemo3.common.utils.streamFrom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: HomeRepository
) : HomeViewModel() {

    override val dataState: MutableStateFlow<BaseUiState<List<HomeBaseEntity.HomeEntity>>> =
        MutableStateFlow(BaseUiState.Success(data = null, loading = true))

    init {
        viewModelScope.apply {
            launch {
                delay(2000)
                dataState.streamFrom(repository.dataStore, "")
            }
        }
    }

    override fun getData() {
        viewModelScope.apply {
            launch { dataState.streamFrom(repository.dataStore, "") }
        }
    }
}