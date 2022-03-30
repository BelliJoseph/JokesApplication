package com.example.jokeapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jokeapplication.JokesApp
import com.example.jokeapplication.rest.JokeRepository
import com.example.jokeapplication.viewmodel.JokesViewModel
import com.example.jokeapplication.viewmodel.ViewModelFactory
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var jokeRepository: JokeRepository

    protected val viewModel by lazy{
        ViewModelProvider(requireActivity(), ViewModelFactory(jokeRepository)) [JokesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        JokesApp.jokesComponent.inject(this)
    }

}