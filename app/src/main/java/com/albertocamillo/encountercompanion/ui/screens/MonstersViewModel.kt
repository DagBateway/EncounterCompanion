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
import com.albertocamillo.encountercompanion.data.MonsterDetails
import com.albertocamillo.encountercompanion.data.MonsterIndex

sealed interface MonstersIndexListUiState {
    data class Success(val monstersIndexListSize: String) : MonstersIndexListUiState
    object Error : MonstersIndexListUiState
    object Loading : MonstersIndexListUiState
}

sealed interface MonstersDetailsUiState {
    data class Success(val monstersDetails: MonsterDetails) : MonstersDetailsUiState
    object Error : MonstersDetailsUiState
    object Loading : MonstersDetailsUiState
}

class MonstersViewModel(private val encounterCompanionRepository: EncounterCompanionRepository) :
    ViewModel() {
    var monstersIndexListUiState: MonstersIndexListUiState by mutableStateOf(
        MonstersIndexListUiState.Loading
    )
        private set

    var monstersDetailsUiState: MonstersDetailsUiState by mutableStateOf(
        MonstersDetailsUiState.Loading
    )
        private set

    init {
        getMonsterIndexList()
    }

    private fun getMonsterIndexList() {
        viewModelScope.launch {
            try {
                val monsterIndexList = encounterCompanionRepository.getMonsterIndexList()
                monstersIndexListUiState = MonstersIndexListUiState.Success("Success: ${monsterIndexList.count} Monsters retrieved")
            } catch (e: IOException) {
                MonstersIndexListUiState.Error
            }

        }
    }

    private fun getMonstersDetails(monsterIndex: MonsterIndex) {
        viewModelScope.launch {
            try {
                val monsterDetails = encounterCompanionRepository.getMonsterDetails(monsterIndex.url)
                monstersDetailsUiState = MonstersDetailsUiState.Success(monsterDetails)
            } catch (e: IOException) {
                MonstersDetailsUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as EncounterCompanionApplication)
                val encounterCompanionRepository =
                    application.container.encounterCompanionRepository
                MonstersViewModel(encounterCompanionRepository = encounterCompanionRepository)
            }
        }
    }
}