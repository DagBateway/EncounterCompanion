package com.albertocamillo.encountercompanion.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
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
import com.albertocamillo.encountercompanion.data.MonsterIndex
import com.albertocamillo.encountercompanion.data.MonsterIndexList

@Composable
fun HomeScreen(
    monstersIndexListUiState: MonstersIndexListUiState,
    monstersDetailsUiState: MonstersDetailsUiState,
    modifier: Modifier = Modifier
) {
    when (monstersIndexListUiState) {
        is MonstersIndexListUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MonstersIndexListUiState.Success -> ResultScreen(
            monstersIndexListUiState.monstersIndexList,
            modifier = modifier.fillMaxWidth()
        )

        is MonstersIndexListUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
//    when (monstersDetailsUiState) {
//        is MonstersDetailsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
//        is MonstersDetailsUiState.Success -> ResultScreen(
//            monstersDetailsUiState.monstersDetails.name,
//            modifier = modifier.fillMaxWidth()
//        )
//
//        is MonstersDetailsUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
//    }
}

/**
 * ResultScreen displaying number of monsters retrieved.
 */
@Composable
fun ResultScreen(monsterIndexList: MonsterIndexList, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        MonsterIndexListColumn(monsterIndexList.results)
    }
}

@Composable
fun MonsterIndexCard(monsterName: String, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = monsterName, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun MonsterIndexListColumn(monsterIndexList: List<MonsterIndex>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(monsterIndexList) { monsterIndex ->
            MonsterIndexCard(monsterName = monsterIndex.name)
        }
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
fun PreviewMonsterIndexCard() {
    MonsterIndexCard(monsterName = "Aboleth")
}
