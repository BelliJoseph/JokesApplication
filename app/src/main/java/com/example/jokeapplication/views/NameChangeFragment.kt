package com.example.jokeapplication.views

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.jokeapplication.databinding.FragmentNameChangeBinding
import com.example.jokeapplication.model.Jokes
import com.example.jokeapplication.utils.JokeState


class NameChangeFragment : BaseFragment() {

    private val binding by lazy{
        FragmentNameChangeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding.nameChangeJokeButton.setOnClickListener {
            val userInput = binding.changeNameEditText.text.toString()
            val checkInput = nameFormatCheck(userInput)
            if(checkInput){
                binding.nameChangeErrorMessage.visibility = View.GONE
                viewModel.getRandomJokeWithName()
            }else{
                binding.nameChangeErrorMessage.visibility = View.VISIBLE
            }
        }

        viewModel.jokes.observe(viewLifecycleOwner){ state ->
            when(state){
                is JokeState.LOADING ->{
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_LONG).show()
                }
                is JokeState.SUCCESS<*> ->{
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
                is JokeState.ERROR ->{
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

        binding.nameChangeMenuButton.setOnClickListener {
            viewModel.userChoiceFirstName = null
            viewModel.userChoiceLastName = null
            viewModel.setLiveDataToDefault()
            findNavController().navigateUp()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun nameFormatCheck(userInput: String): Boolean{
        val spacePosition = userInput.trim().indexOf(" ")
        if(spacePosition == -1 && userInput.length > 1){
            val capitalFirst = userInput.trim().replaceFirstChar { it.uppercase() }
            viewModel.userChoiceFirstName = capitalFirst
            viewModel.userChoiceLastName = null
            return true
        }

        return if(spacePosition > 1 && spacePosition < userInput.length){
            val firstName = userInput.trim().substring(0, spacePosition)
            val lastName = userInput.substring(spacePosition).trim()

            val capitalFirst = firstName.replaceFirstChar { it.uppercase() }
            val capitalLast = lastName.replaceFirstChar { it.uppercase() }
            viewModel.userChoiceFirstName = capitalFirst
            viewModel.userChoiceLastName = capitalLast
            true
        }else{
            false
        }

    }

}