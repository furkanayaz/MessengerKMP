package org.ayaz.messenger.domain.util

sealed interface Resource<T> where T: Any {
    data class Error<T: Any>(val message: String): Resource<T>
    data class Success<T: Any>(val item: T? = null): Resource<T>
}