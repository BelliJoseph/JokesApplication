package com.example.jokeapplication.views

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapplication.adapter.JokeAdapter
import com.example.jokeapplication.databinding.FragmentJokeListBinding
import com.example.jokeapplication.model.ListJokes
import com.example.jokeapplication.utils.JokeState


class JokeListFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("JokeListFragment", "onCreate")
    }

    private val binding by lazy{
        FragmentJokeListBinding.inflate(layoutInflater)
    }

    private val jokeAdapter by lazy{
        JokeAdapter()
    }

    private val recyclerView by lazy{
        binding.jokeListRecyclerView
    }

    private var firstInsert: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("JokeListFragment", "onCreateView")

        recyclerView.apply {
            val linearManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            layoutManager = linearManager
            adapter = jokeAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    //only load more if the last joke is visible in recyclerView
                    if(linearManager.findLastCompletelyVisibleItemPosition()
                        >= linearManager.itemCount - 1) {
                        viewModel.getRandomJokeList()
                    }
                }
            })
        }

        viewModel.jokes.observe(viewLifecycleOwner){ state ->
            when(state){
                is JokeState.LOADING ->{
                    binding.listProgressBar.visibility = View.VISIBLE
                }
                is JokeState.SUCCESS<*> ->{
                    val tempListJokes = state.result as ListJokes
                    val jokeList = tempListJokes.jokes
                    if(firstInsert){
                        firstInsert = false
                        binding.listProgressBar.visibility = View.GONE
                        jokeAdapter.startNewJokeList(jokeList)
                    }else{
                        binding.listProgressBar.visibility = View.GONE
                        jokeAdapter.addJokesToList(jokeList)
                    }

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

        viewModel.getRandomJokeList()

        binding.jokeListBackButton.setOnClickListener {
            viewModel.setLiveDataToDefault()
            findNavController().navigateUp()
        }

        // Inflate the layout for this fragment
        return binding.root
    }



}