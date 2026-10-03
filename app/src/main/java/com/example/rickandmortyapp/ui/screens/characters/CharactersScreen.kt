package com.example.rickandmortyapp.ui.screens.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.ui.components.CardCharacterItem
import com.example.rickandmortyapp.ui.navigation.Screen


@Composable
fun CharactersScreen(
    charactersViewModel: CharactersViewModel = hiltViewModel(),
    navController: NavController
) {
    val charactersList = charactersViewModel.characters.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentWindowInsets = WindowInsets.safeContent,
        topBar = { TopBar() }
    ) { pv ->
        when (charactersList.loadState.refresh) {
            is LoadState.Loading -> {
                Loading(pv)
            }

            is LoadState.Error -> {
                Error(pv, charactersList)
            }

            else -> {
                ShowCharacters(
                    charactersList,
                    navController,
                    PaddingValues(pv.calculateBottomPadding())
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.characters_list_to_bar),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors()
            .copy(containerColor = Color(0xFF181A20))
    )
}


@Composable
private fun Loading(pv: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(pv),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}


@Composable
private fun Error(
    pv: PaddingValues,
    charactersList: LazyPagingItems<Character>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(pv),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.error_carga_mensaje),
            fontWeight = FontWeight.Bold
        )
        Button(onClick = { charactersList.retry() }) { Text(stringResource(R.string.descargar)) }
    }
}

@Composable
fun ShowCharacters(
    charactersList: LazyPagingItems<Character>,
    navController: NavController,
    bottomPadding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF181A20))
            .padding(bottomPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(charactersList.itemCount) { index ->
            val character = charactersList[index]
            character?.let {
                CardCharacterItem(it) { navController.navigate("${Screen.Detail.route}/${it.id}") }
            }
        }

        if (charactersList.loadState.append is LoadState.Loading) {
            item { CircularProgressIndicator() }
        }
    }
}