package com.example.jokeapplication.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jokeapplication.R
import com.example.jokeapplication.databinding.FragmentMainBinding


class MainFragment : BaseFragment() {

    private val binding by lazy{
        FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.jokeListButton.setOnClickListener {
            Log.d("MainFragment", "Click JokeListButton")
            findNavController().navigate(R.id.action_mainFragment_to_jokeListFragment)
        }

        binding.nameChangeButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_nameChangeFragment)
        }

        binding.randomJokeButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_randomJokeFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }


}