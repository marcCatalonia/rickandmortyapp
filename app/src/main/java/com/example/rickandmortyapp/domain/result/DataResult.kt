package com.example.rickandmortyapp.domain.result

import com.example.rickandmortyapp.domain.error.DomainError

sealed interface DataResult<out T> {
    data class Success<out T>(val data: T): DataResult<T>
    data class Failure(val error: DomainError): DataResult<Nothing>
}