package com.example.jokeapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jokeapplication.rest.JokeAPI
import com.example.jokeapplication.rest.JokeRepository
import java.security.Provider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val jokeRepository: JokeRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JokesViewModel(jokeRepository) as T
    }


}