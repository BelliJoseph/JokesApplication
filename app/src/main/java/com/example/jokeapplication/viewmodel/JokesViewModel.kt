package com.example.jokeapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jokeapplication.rest.JokeRepository
import javax.inject.Inject

class JokesViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
): ViewModel() {


}