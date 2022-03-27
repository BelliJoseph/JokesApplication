package com.example.jokeapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jokeapplication.R
import com.example.jokeapplication.databinding.FragmentRandomJokeBinding

class RandomJokeFragment : BaseFragment() {

    private val binding by lazy{
        FragmentRandomJokeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        return binding.root
    }


}