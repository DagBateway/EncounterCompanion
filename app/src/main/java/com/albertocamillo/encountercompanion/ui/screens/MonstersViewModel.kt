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

sealed interface MonstersUiState {
    data class Success(val monsters: String) :  MonstersUiState
    object Error : MonstersUiState
    object Loading : MonstersUiState
}
class MonstersViewModel(private val encounterCompanionRepository: EncounterCompanionRepository): ViewModel() {
    var monstersUiState: MonstersUiState by mutableStateOf(MonstersUiState.Loading)
        private set
    init {
        getMonsters()
    }

    private fun getMonsters() {
        viewModelScope.launch {
            monstersUiState = try{
                val listResult = encounterCompanionRepository.getMonsterIndexList()
                MonstersUiState.Success("Success: ${listResult.count} Monsters retrieved")
            }catch (e: IOException){
                MonstersUiState.Error
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as EncounterCompanionApplication)
                val encounterCompanionRepository = application.container.encounterCompanionRepository
                MonstersViewModel(encounterCompanionRepository = encounterCompanionRepository)
            }
        }
    }
}