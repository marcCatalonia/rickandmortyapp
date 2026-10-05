package com.example.rickandmortyapp.data.mapper

import com.example.rickandmortyapp.domain.error.DomainError
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toDomainError(): DomainError = when (this) {
    is HttpException -> when (code()) {
        404 -> DomainError.NotFound
        in 500..599 -> DomainError.Server
        else -> DomainError.Unknown(this)
    }
    is IOException -> DomainError.NoConnection          // Unknown Host, Timeout, Connection

    else -> DomainError.Unknown(this)           // Ex: Bad JSON
}
