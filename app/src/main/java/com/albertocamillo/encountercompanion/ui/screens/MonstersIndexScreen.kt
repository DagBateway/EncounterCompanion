@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.albertocamillo.encountercompanion.R
import com.albertocamillo.encountercompanion.data.MonsterIndex
import com.albertocamillo.encountercompanion.data.MonsterIndexList

@Composable
fun MonstersIndexScreen(
    monstersIndexListWebRequestUiState: MonstersIndexListWebRequestUiState,
    onMonsterCardClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    when (monstersIndexListWebRequestUiState) {
        is MonstersIndexListWebRequestUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MonstersIndexListWebRequestUiState.Success -> ResultScreen(
            monstersIndexListWebRequestUiState.monstersIndexList,
            onMonsterCardClicked,
            modifier = modifier.fillMaxWidth()
        )

        is MonstersIndexListWebRequestUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}


/**
 * ResultScreen displaying number of monsters retrieved.
 */
@Composable
fun ResultScreen(monsterIndexList: MonsterIndexList, onMonsterCardClicked: (String) -> Unit, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        MonsterIndexListColumn(onMonsterCardClicked, monsterIndexList.results)
    }
}

@Composable
fun MonsterIndexCard(onMonsterCardClicked: (String) -> Unit, monsterIndex: MonsterIndex) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        onClick = {
            onMonsterCardClicked(monsterIndex.index)
        },
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = monsterIndex.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun MonsterIndexListColumn(
    onMonsterCardClicked: (String) -> Unit,
    monsterIndexList: List<MonsterIndex>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(monsterIndexList) { monsterIndex ->
            MonsterIndexCard(onMonsterCardClicked, monsterIndex = monsterIndex)
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
    val monster = MonsterIndex(name = "Aboleth", index = "aboleth", url = "/api/aboleth")
    MonsterIndexCard(monsterIndex = monster, onMonsterCardClicked = {})
}
