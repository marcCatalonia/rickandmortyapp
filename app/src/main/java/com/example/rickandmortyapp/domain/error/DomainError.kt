package com.example.rickandmortyapp.domain.error

sealed interface DomainError {
    data object NoConnection : DomainError              // Internet, DNS, Timeout
    data object NotFound : DomainError                  // 404
    data object Server : DomainError                    // 5XX
    data class Unknown(val cause: Throwable? = null) : DomainError
}

// Error compatible with PagingSource
class DomainException(val error: DomainError) : Exception()


//For the UI to convert any Throwable into a DomainError
fun Throwable.asDomainError(): DomainError =
    (this as? DomainException)?.error ?: DomainError.Unknown(this)
