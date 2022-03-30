package com.example.jokeapplication.di


import com.example.jokeapplication.views.*
import dagger.Component

@Component(modules = [
    ApplicationModule::class,
    NetworkModule::class
])
interface JokesComponent {

    fun inject(baseFragment: BaseFragment)
}