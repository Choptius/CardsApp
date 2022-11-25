package com.cardsapp.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf

typealias FlowResult<T> = Flow<Result<T>>

sealed interface Result<T> {
    class Success<T>(
        val data: T
    ) : Result<T>

    data class Error<T>(
        val failure: Failure,
        val exception: Exception? = null
    ) : Result<T>

    class Loading<T> : Result<T>
}

fun <T, R> Result<T>.mapSuccess(transform: (T) -> (R)): Result<R> {
    return when(this) {
        is Result.Success -> Result.Success(transform(this.data))
        is Result.Loading -> Result.Loading()
        is Result.Error -> Result.Error(
            failure = failure,
            exception = this.exception
        )
    }
}

class None
