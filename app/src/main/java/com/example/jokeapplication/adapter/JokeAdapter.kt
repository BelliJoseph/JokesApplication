package com.example.jokeapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapplication.R
import com.example.jokeapplication.model.Joke

class JokeAdapter(
    private val jokeList: MutableList<Joke> = mutableListOf()
): RecyclerView.Adapter<JokeViewHolder>() {

    fun startNewJokeList(listOfJokes: List<Joke>){
        jokeList.clear()
        jokeList.addAll(listOfJokes)
        notifyDataSetChanged()
    }

    fun addJokesToList(listOfJokes: List<Joke>){
        val currentListSize = jokeList.size
        jokeList.addAll(listOfJokes)
        notifyItemRangeInserted(currentListSize -1, listOfJokes.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val jokeView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return JokeViewHolder(jokeView)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = jokeList[position]
        holder.bind(joke)
    }

    override fun getItemCount(): Int = jokeList.size


}

class JokeViewHolder(
    jokeView: View
): RecyclerView.ViewHolder(jokeView){

    private val jokeText: TextView = itemView.findViewById(R.id.jokeListText)

    fun bind(joke: Joke){

        jokeText.text = joke.theJoke

    }

}