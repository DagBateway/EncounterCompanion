@file:OptIn(ExperimentalMaterial3Api::class)

package com.albertocamillo.encountercompanion.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.albertocamillo.encountercompanion.ui.screens.MonstersIndexViewModel
import com.albertocamillo.encountercompanion.R
import com.albertocamillo.encountercompanion.ui.screens.MonstersIndexScreen
import com.albertocamillo.encountercompanion.ui.screens.MonsterDetailsScreen
import com.albertocamillo.encountercompanion.ui.screens.MonsterDetailsViewModel

enum class EncounterCompanionScreens() {
    MonstersIndex,
    MonsterDetails
}

@Composable
fun EncounterCompanionApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { EncounterCompanionTopAppBar(scrollBehavior = scrollBehavior) }
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val monstersIndexViewModel: MonstersIndexViewModel =
                viewModel(factory = MonstersIndexViewModel.Factory)
            val monsterDetailsViewModel: MonsterDetailsViewModel =
                viewModel(factory = MonsterDetailsViewModel.Factory)

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = EncounterCompanionScreens.MonstersIndex.name,
                modifier = Modifier.padding(4.dp)
            ) {
                composable(route = EncounterCompanionScreens.MonstersIndex.name) {
                    MonstersIndexScreen(monstersIndexListWebRequestUiState = monstersIndexViewModel.monstersIndexListWebRequestUiState,
                        onMonsterCardClicked =
                        { monsterName ->
                            navController.navigate("MonsterDetails/${monsterName}")
                        })

                }
                composable(route = "MonsterDetails/{monsterName}")
                { backStackEntry ->
                    val monsterName = backStackEntry.arguments?.getString("monsterName")
                    MonsterDetailsScreen(
                        monsterName = monsterName ?: "",
                        monsterDetailsViewModel = monsterDetailsViewModel,
                        monstersDetailsWebRequestUiState = monsterDetailsViewModel.monstersDetailsWebRequestUiState
                    )
                }
            }
        }
    }
}

@Composable
fun EncounterCompanionTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}