@file:OptIn(ExperimentalMaterial3Api::class)

package com.albertocamillo.encountercompanion.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.albertocamillo.encountercompanion.data.MonsterDetails
import com.albertocamillo.encountercompanion.data.MonsterDetailsUiState
import kotlinx.coroutines.coroutineScope

@Composable
fun MonsterDetailsScreen(
    monsterName: String,
    monsterDetailsViewModel: MonsterDetailsViewModel,
    monstersDetailsWebRequestUiState: MonstersDetailsWebRequestUiState,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(monsterName) {
        monsterDetailsViewModel.getMonstersDetails(monsterName)
    }

    when (monstersDetailsWebRequestUiState) {
        is MonstersDetailsWebRequestUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MonstersDetailsWebRequestUiState.Success -> MonsterDetails(monstersDetailsWebRequestUiState.monstersDetails,modifier = modifier.fillMaxWidth())
        is MonstersDetailsWebRequestUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

/**
 * MonsterDetails displaying the monster details.
 */
@Composable
fun MonsterDetails(monsterDetails: MonsterDetails, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = monsterDetails.name)
    }
}
