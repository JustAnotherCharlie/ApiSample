package com.example.apisampleapp.usecase.shared

/**
 * Abstract class to establish
 */
abstract class UseCase<INPUT, OUTPUT> {
    abstract suspend fun execute(input: INPUT): OUTPUT
}