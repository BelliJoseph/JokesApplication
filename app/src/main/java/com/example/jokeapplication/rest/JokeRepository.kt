package com.example.jokeapplication.rest

import com.example.jokeapplication.model.Joke
import retrofit2.Response

interface JokeRepository {
    suspend fun getRandomJoke(): Response<Joke>

    suspend fun getNumberOfRandomJokes(
        number: Int = JokeAPI.NUMBER_OF_JOKES
    ): Response<List<Joke>>

    suspend fun getExcludedNumberRandomJokes(
        number: Int = JokeAPI.NUMBER_OF_JOKES,
        exclude: String = JokeAPI.EXCLUDE_EXPLICIT
    ): Response<List<Joke>>

    suspend fun getExcludedRandomJoke(
        exclude: String = JokeAPI.EXCLUDE_EXPLICIT
    ): Response<Joke>

    suspend fun getRandomJokeWithName(
        firstName: String,
        lastName: String?
    ): Response<Joke>

}

class JokesRepositoryImpl(
    private val jokeAPI: JokeAPI
): JokeRepository{
    override suspend fun getRandomJoke(): Response<Joke> {
        return jokeAPI.getRandomJoke()
    }

    override suspend fun getNumberOfRandomJokes(number: Int): Response<List<Joke>> {
        return jokeAPI.getNumberOfRandomJokes(number = number)
    }

    override suspend fun getExcludedNumberRandomJokes(
        number: Int,
        exclude: String
    ): Response<List<Joke>> {
        return jokeAPI.getExcludedNumberRandomJokes(number = number, exclude = exclude)
    }

    override suspend fun getExcludedRandomJoke(exclude: String): Response<Joke> {
        return jokeAPI.getExcludedRandomJoke(exclude = exclude)
    }

    override suspend fun getRandomJokeWithName(
        firstName: String,
        lastName: String?
    ): Response<Joke> {
        return jokeAPI.getRandomJokeWithName(firstName = firstName, lastName = lastName)
    }


}