package com.albertocamillo.encountercompanion.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.albertocamillo.encountercompanion.EncounterCompanionApplication
import java.io.IOException
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.albertocamillo.encountercompanion.data.EncounterCompanionRepository
import com.albertocamillo.encountercompanion.data.MonsterIndexList

sealed interface MonstersIndexListWebRequestUiState {
    data class Success(val monstersIndexList: MonsterIndexList) : MonstersIndexListWebRequestUiState
    object Error : MonstersIndexListWebRequestUiState
    object Loading : MonstersIndexListWebRequestUiState
}

class MonstersIndexViewModel(private val encounterCompanionRepository: EncounterCompanionRepository) :
    ViewModel() {

    var monstersIndexListWebRequestUiState: MonstersIndexListWebRequestUiState by mutableStateOf(
        MonstersIndexListWebRequestUiState.Loading
    )
        private set

    init {
        getMonsterIndexList()
    }

    private fun getMonsterIndexList() {
        viewModelScope.launch {
            try {
                val monsterIndexList = encounterCompanionRepository.getMonsterIndexList()
                monstersIndexListWebRequestUiState = MonstersIndexListWebRequestUiState.Success(monsterIndexList)
            } catch (e: IOException) {
                MonstersIndexListWebRequestUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as EncounterCompanionApplication)
                val encounterCompanionRepository =
                    application.container.encounterCompanionRepository
                MonstersIndexViewModel(encounterCompanionRepository = encounterCompanionRepository)
            }
        }
    }
}