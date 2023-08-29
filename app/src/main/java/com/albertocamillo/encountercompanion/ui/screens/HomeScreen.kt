package com.albertocamillo.encountercompanion.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.albertocamillo.encountercompanion.ui.theme.EncounterCompanionTheme
import com.albertocamillo.encountercompanion.R

@Composable
fun HomeScreen(
    monstersUiState: String, modifier: Modifier = Modifier
) {
    ResultScreen(monstersUiState, modifier)
}

/**
 * ResultScreen displaying number of monsters retrieved.
 */
@Composable
fun ResultScreen(monsters: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = monsters)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    EncounterCompanionTheme {
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}
