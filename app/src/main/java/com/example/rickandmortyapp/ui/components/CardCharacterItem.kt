package com.example.rickandmortyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.model.Character

@Composable
fun CardCharacterItem(
    character: Character = Character(
        id = 1,
        name = "Morty Smith",
        status = "Alive",
        type = "",
        gender = "Male",
        species = "Human",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        origin = "Earth",
        location = "Earth"
    ),
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(300.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            CharacterImage(image = character.imageUrl)

            CharacterGradient()

            CharacterOverlayInfo(character)
        }
    }
}

@Preview
@Composable
fun CharacterImage(modifier: Modifier = Modifier, image: String = "https://rickandmortyapi.com/api/character/avatar/2.jpeg"){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image).crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.loading_img),
        error = painterResource(R.drawable.no_image),
        modifier = modifier
            .width(300.dp)
            .height(300.dp)
    )
}


@Composable
fun CharacterGradient() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Transparent,
                        Color.Black.copy(alpha = 0.6f),
                        Color.Black.copy(alpha = 0.9f)
                    ),
                    startY = 300f
                )
            )
    )
}



@Composable
private fun CharacterOverlayInfo(character: Character) {

    Row(
        modifier = Modifier
            .padding(12.dp),
        horizontalArrangement = (Arrangement.SpaceBetween),
        verticalAlignment = Alignment.CenterVertically

    ) {

        Text(
            text = character.name,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start)
                .background(
                    Color.Black.copy(alpha = 0.65f),
                    RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )


        StatusRow(
            status = character.status,
            species = character.species
        )
    }
}



@Composable
fun StatusRow(
    status: String,
    species: String
) {

    val color = when (status) {
        "Alive" -> Color(0xFF55CC44)
        "Dead" -> Color.Red
        else -> Color.Gray
    }

    Row(
        modifier = Modifier.padding(start = 6.dp)
            .background(
                Color.Black.copy(alpha = 0.65f),
                RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(8.dp)
                .background(color, CircleShape)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = "$status · $species",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier

        )
    }
}

