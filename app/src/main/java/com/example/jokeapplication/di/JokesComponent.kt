package com.example.jokeapplication.di

import com.example.jokeapplication.MainActivity
import com.example.jokeapplication.views.JokeListFragment
import com.example.jokeapplication.views.MainFragment
import com.example.jokeapplication.views.NameChangeFragment
import com.example.jokeapplication.views.RandomJokeFragment
import dagger.Component

@Component(modules = [
    ApplicationModule::class,
    NetworkModule::class
])
interface JokesComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(jokeListFragment: JokeListFragment)
    fun inject(mainFragment: MainFragment)
    fun inject(nameChangeFragment: NameChangeFragment)
    fun inject(randomJokeFragment: RandomJokeFragment)
}