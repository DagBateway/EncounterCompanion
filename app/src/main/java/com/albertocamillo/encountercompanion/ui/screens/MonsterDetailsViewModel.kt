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
import com.albertocamillo.encountercompanion.data.MonsterDetailsUiState
import com.albertocamillo.encountercompanion.data.MonsterIndex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


sealed interface MonstersDetailsWebRequestUiState {
    data class Success(val monstersDetails: MonsterDetails) : MonstersDetailsWebRequestUiState
    object Error : MonstersDetailsWebRequestUiState
    object Loading : MonstersDetailsWebRequestUiState
}

class MonsterDetailsViewModel(private val encounterCompanionRepository: EncounterCompanionRepository) :
    ViewModel() {

    var monstersDetailsWebRequestUiState: MonstersDetailsWebRequestUiState by mutableStateOf(
        MonstersDetailsWebRequestUiState.Loading
    )
        private set

    private val _uiState = MutableStateFlow(MonsterDetailsUiState())
    var uiState: StateFlow<MonsterDetailsUiState> = _uiState.asStateFlow()

    fun setMonsterName(monsterName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                monsterName = monsterName
            )
        }
    }

    fun getMonstersDetails(monsterName: String) {
        viewModelScope.launch {
            try {
                val monsterDetails = encounterCompanionRepository.getMonsterDetails(monsterName)
                monstersDetailsWebRequestUiState = MonstersDetailsWebRequestUiState.Success(monsterDetails)
            } catch (e: IOException) {
                MonstersDetailsWebRequestUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as EncounterCompanionApplication)
                val encounterCompanionRepository =
                    application.container.encounterCompanionRepository
                MonsterDetailsViewModel(encounterCompanionRepository = encounterCompanionRepository)
            }
        }
    }
}