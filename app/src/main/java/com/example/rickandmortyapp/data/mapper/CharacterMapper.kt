package com.example.rickandmortyapp.data.mapper

import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.data.dto.CharacterDto

fun CharacterDto.toDomain(): Character{
    return Character(
        id = id,
        gender = gender,
        name = name,
        type = type,
        status = status,
        species = species,
        imageUrl = image,
        origin = origin.name,
        location = location.name
    )
}