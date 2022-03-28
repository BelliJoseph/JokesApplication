package com.example.jokeapplication.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.jokeapplication.rest.JokeRepository
import com.example.jokeapplication.viewmodel.JokesViewModel
import com.example.jokeapplication.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(
    private val applicationContext: Context
) {

    @Provides
    fun providesContext(): Context{
        return applicationContext
    }


    @Provides
    fun providesViewModelFactory(jokeRepository: JokeRepository) =
        ViewModelFactory(jokeRepository)

//    @Provides
//    fun providesViewModel(viewModelFactory: ViewModelFactory): JokesViewModel =
//        ViewModelProvider(applicationContext.)

}