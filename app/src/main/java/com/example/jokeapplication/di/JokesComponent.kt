package com.example.jokeapplication.di

import com.example.jokeapplication.MainActivity
import dagger.Component

@Component(modules = [
    ApplicationModule::class,
    NetworkModule::class
])
interface JokesComponent {

    fun inject(mainActivity: MainActivity)
}