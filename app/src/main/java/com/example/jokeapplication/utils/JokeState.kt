package com.example.jokeapplication.utils

sealed class JokeState{
    object DEFAULT: JokeState()
    object LOADING: JokeState()
    class SUCCESS<T>(val result: T): JokeState()
    class ERROR(val error: Throwable): JokeState()
}
