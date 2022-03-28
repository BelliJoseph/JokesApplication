package com.example.jokeapplication.rest

import com.example.jokeapplication.model.Jokes
import com.example.jokeapplication.model.ListJokes
import retrofit2.Response

interface JokeRepository {
    suspend fun getRandomJoke(): Response<Jokes>

    suspend fun getNumberOfRandomJokes(
        number: Int = JokeAPI.NUMBER_OF_JOKES
    ): Response<List<ListJokes>>

    suspend fun getExcludedNumberRandomJokes(
        number: Int = JokeAPI.NUMBER_OF_JOKES,
        exclude: String = JokeAPI.EXCLUDE_EXPLICIT
    ): Response<List<ListJokes>>

    suspend fun getExcludedRandomJoke(
        exclude: String = JokeAPI.EXCLUDE_EXPLICIT
    ): Response<Jokes>

    suspend fun getRandomJokeWithName(
        firstName: String?,
        lastName: String?
    ): Response<Jokes>

}

class JokesRepositoryImpl(
    private val jokeAPI: JokeAPI
): JokeRepository{
    override suspend fun getRandomJoke(): Response<Jokes> {
        return jokeAPI.getRandomJoke()
    }

    override suspend fun getNumberOfRandomJokes(number: Int): Response<List<ListJokes>> {
        return jokeAPI.getNumberOfRandomJokes(number = number)
    }

    override suspend fun getExcludedNumberRandomJokes(
        number: Int,
        exclude: String
    ): Response<List<ListJokes>> {
        return jokeAPI.getExcludedNumberRandomJokes(number = number, exclude = exclude)
    }

    override suspend fun getExcludedRandomJoke(exclude: String): Response<Jokes> {
        return jokeAPI.getExcludedRandomJoke(exclude = exclude)
    }

    override suspend fun getRandomJokeWithName(
        firstName: String?,
        lastName: String?
    ): Response<Jokes> {
        return jokeAPI.getRandomJokeWithName(firstName = firstName, lastName = lastName)
    }


}