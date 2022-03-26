package com.example.jokeapplication

import android.app.Application
import com.example.jokeapplication.di.ApplicationModule
import com.example.jokeapplication.di.DaggerJokesComponent
import com.example.jokeapplication.di.JokesComponent

class JokesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        jokesComponent = DaggerJokesComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object{
        lateinit var jokesComponent : JokesComponent
    }
}