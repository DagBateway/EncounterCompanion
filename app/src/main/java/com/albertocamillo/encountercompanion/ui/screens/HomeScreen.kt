package com.albertocamillo.encountercompanion.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.albertocamillo.encountercompanion.ui.theme.EncounterCompanionTheme
import com.albertocamillo.encountercompanion.R

@Composable
fun HomeScreen(
    monstersUiState: MonstersUiState, modifier: Modifier = Modifier
) {
    when (monstersUiState) {
        is MonstersUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MonstersUiState.Success -> ResultScreen(monstersUiState.monsters, modifier = modifier.fillMaxWidth())
        is MonstersUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
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

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    EncounterCompanionTheme {
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}
