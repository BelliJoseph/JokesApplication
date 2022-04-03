package com.example.jokeapplication.views

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.jokeapplication.databinding.FragmentRandomJokeBinding
import com.example.jokeapplication.model.Jokes
import com.example.jokeapplication.utils.JokeState

class RandomJokeFragment : BaseFragment() {

    private val binding by lazy{
        FragmentRandomJokeBinding.inflate(layoutInflater)
    }

    private var checked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val check = binding.randomJokeCheckBox

        check.setOnClickListener {
            checked = check.isChecked
        }

        viewModel.jokes.observe(viewLifecycleOwner){ state ->
            when(state){
                is JokeState.LOADING -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_LONG).show()
                }
                is JokeState.SUCCESS<*> -> {
                    val aJoke = state.result as Jokes
                    val thisJoke = aJoke.jokes.theJoke
                    AlertDialog.Builder(requireContext())
                        .setMessage(thisJoke)
                        .setPositiveButton("Dismiss"){
                            DialogInterface, i ->
                            DialogInterface.dismiss() }
                        .create()
                        .show()

                }
                is JokeState.ERROR -> {
                    AlertDialog.Builder(requireContext())
                        .setMessage(state.error.toString())
                        .setPositiveButton("Dismiss"){
                                DialogInterface, i ->
                            DialogInterface.dismiss() }
                        .create()
                        .show()
                }
            }
        }

        binding.randomJokeButton.setOnClickListener{
            if(checked){
                viewModel.getCleanRandomJoke()
            }else{
                viewModel.getRandomJoke()
            }
        }

        binding.randomJokeGoBackToMenu.setOnClickListener {
            viewModel.setLiveDataToDefault()
            findNavController().navigateUp()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}