package com.example.rickandmortyapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.ui.components.CharacterImage
import com.example.rickandmortyapp.ui.components.StatusRow

@Composable
fun CharacterDetailScreen(
    paddingValues: PaddingValues,
    characterDetailViewModel: CharacterDetailViewModel = hiltViewModel()
) {

    val character = characterDetailViewModel.getCharacterById()

    Scaffold(
        modifier = Modifier
            .background(Color(0xFF181A20))
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        character?.let { char ->
            LazyColumn(
                modifier = Modifier
                    .background(Color(0xFF181A20))
                    .padding(it)
            ) {

                item {
                    CharacterHeader(char)
                }

                item {
                    CharacterInfoSection(
                        title = "Status",
                        value = char.status
                    )
                }

                item {
                    CharacterInfoSection(
                        title = "Species",
                        value = char.species
                    )
                }

                item {
                    CharacterInfoSection(
                        title = "Origin",
                        value = char.origin
                    )
                }

                item {
                    CharacterInfoSection(
                        title = "Location",
                        value = char.location
                    )
                }
            }
        }
    }

}


@Composable
private fun CharacterHeader(character: Character) {


    CharacterImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
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
                .fillMaxSize()
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


