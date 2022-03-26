package com.example.jokeapplication.rest

import com.example.jokeapplication.model.Joke
import retrofit2.Response

interface JokeRepository {
    suspend fun getRandomJoke(): Response<Joke>

    suspend fun getNumberOfRandomJokes(
        number: Int = Services.NUMBER_OF_JOKES
    ): Response<List<Joke>>

    suspend fun getExcludedNumberRandomJokes(
        number: Int = Services.NUMBER_OF_JOKES,
        exclude: String = Services.EXCLUDE_EXPLICIT
    ): Response<List<Joke>>

    suspend fun getExcludedRandomJoke(
        exclude: String = Services.EXCLUDE_EXPLICIT
    ): Response<Joke>

    suspend fun getRandomJokeWithName(
        firstName: String,
        lastName: String?
    ): Response<Joke>

}

class JokesRepositoryImpl(
    private val services: Services
): JokeRepository{
    override suspend fun getRandomJoke(): Response<Joke> {
        return services.getRandomJoke()
    }

    override suspend fun getNumberOfRandomJokes(number: Int): Response<List<Joke>> {
        return services.getNumberOfRandomJokes(number = number)
    }

    override suspend fun getExcludedNumberRandomJokes(
        number: Int,
        exclude: String
    ): Response<List<Joke>> {
        return services.getExcludedNumberRandomJokes(number = number, exclude = exclude)
    }

    override suspend fun getExcludedRandomJoke(exclude: String): Response<Joke> {
        return services.getExcludedRandomJoke(exclude = exclude)
    }

    override suspend fun getRandomJokeWithName(
        firstName: String,
        lastName: String?
    ): Response<Joke> {
        return services.getRandomJokeWithName(firstName = firstName, lastName = lastName)
    }


}