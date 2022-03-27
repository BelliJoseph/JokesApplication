package com.example.jokeapplication

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.jokeapplication.di.ApplicationModule
import com.example.jokeapplication.di.DaggerJokesComponent
import com.example.jokeapplication.di.JokesComponent
import com.example.jokeapplication.viewmodel.JokesViewModel
import dagger.Provides

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