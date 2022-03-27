package com.example.jokeapplication.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jokeapplication.viewmodel.JokesViewModel
import com.example.jokeapplication.viewmodel.ViewModelFactory
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<JokesViewModel> { viewModelFactory }
}