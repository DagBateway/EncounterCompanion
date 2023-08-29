package com.albertocamillo.encountercompanion.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertocamillo.encountercompanion.network.Api
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MonstersViewModel: ViewModel() {
    var monstersUiState: String by mutableStateOf("")
        private set
    init {
        getMonsters()
    }

    private fun getMonsters() {
        viewModelScope.launch {
            val listResult = Api.retrofitService.getMonsters()
            monstersUiState = listResult
        }
    }
}