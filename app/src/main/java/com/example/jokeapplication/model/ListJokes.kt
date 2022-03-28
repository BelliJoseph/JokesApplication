package com.example.jokeapplication.model

import com.google.gson.annotations.SerializedName

data class ListJokes(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val jokes: List<Joke>
)