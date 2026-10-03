package com.example.rickandmortyapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.ui.components.CharacterImage
import com.example.rickandmortyapp.ui.components.StatusRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterDetailViewModel: CharacterDetailViewModel = hiltViewModel()
) {

    val uiState by characterDetailViewModel.uiState.collectAsStateWithLifecycle()
    val generalModifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF181A20))

    Scaffold(
        modifier = generalModifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color(0xFF181A20)),
                title = {
                    (uiState as? CharacterDetailUiState.Success)?.character?.name?.let {
                        Text(
                            it,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                },
            )
        }
    ) {

        when(val state = uiState){
            is CharacterDetailUiState.Loading -> {
                LoadingCharacterDetail(it)
            }
            is CharacterDetailUiState.Success -> {
                ShowCharacter(generalModifier, state.character, it)
            }
            is CharacterDetailUiState.NotFound -> {
                Text(text = stringResource(R.string.character_not_found), color = Color.White)
            }
        }
    }

}

@Composable
private fun ShowCharacter(modifier: Modifier, character: Character, paddingValues: PaddingValues){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        CharacterHeader(Modifier.weight(2f),character)
        SHowCharacterDetail(Modifier.weight(1f), character, bottomPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding()))
    }
}

@Composable
private fun SHowCharacterDetail(modifier: Modifier, character: Character, bottomPadding: PaddingValues){


    val map = mapOf("Status" to character.status, "Species" to character.species, "Origin" to character.origin, "Location" to character.location)
    LazyColumn(
        modifier = modifier
            .background(Color(0xFF181A20))
            .padding(bottomPadding),
    ) {

        map.forEach { (key, value) ->
            item {
                CharacterInfoSection(
                    title = key,
                    value = value
                )
            }
        }
    }
}

@Composable
private fun CharacterHeader(modifier: Modifier,character: Character) {
    CharacterImage(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = Color(0xFF2A2A2A),
                shape = RoundedCornerShape(16.dp)
            ), image = character.imageUrl
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        //.offset(y = 280.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier
                .background(color = Color(0xFF20232B))
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = character.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            StatusRow(
                status = character.status,
                species = character.species
            )
        }
    }
}


@Composable
private fun CharacterInfoSection(
    title: String,
    value: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {

        Column(
            modifier = Modifier
                .background(color = Color(0xFF20232B))
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = title,
                color = Color.White,
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}

//@Preview
@Composable
fun LoadingCharacterDetail(paddingValues: PaddingValues = PaddingValues()){
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}


@Preview
@Composable
fun ScreenPreview(paddingValues: PaddingValues = PaddingValues()){
    Scaffold(
        modifier = Modifier
            .background(Color(0xFF181A20))
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        LoadingCharacterDetail(it)
    }
}


