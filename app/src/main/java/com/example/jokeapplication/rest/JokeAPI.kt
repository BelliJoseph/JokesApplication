package com.example.jokeapplication.rest


import com.example.jokeapplication.model.Joke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokeAPI {

    @GET(RANDOM)
    suspend fun getRandomJoke() : Response<Joke>

    @GET(RANDOM_NUMBER)
    suspend fun getNumberOfRandomJokes(
        @Path("number") number : Int = NUMBER_OF_JOKES
    ) : Response<List<Joke>>

    @GET(RANDOM_NUMBER)
    suspend fun getExcludedNumberRandomJokes(
        @Path("number") number : Int = NUMBER_OF_JOKES,
        @Query("exclude") exclude : String = EXCLUDE_EXPLICIT
    ) : Response<List<Joke>>

    @GET(RANDOM)
    suspend fun getExcludedRandomJoke(
        @Query("exclude") exclude : String = EXCLUDE_EXPLICIT
    ) : Response<Joke>

    @GET(RANDOM)
    suspend fun getRandomJokeWithName(
        @Query("firstName") firstName: String,
        @Query("lastName") lastName: String? = null
    ) : Response<Joke>

    companion object{
        const val BASE_URL = "http://api.icndb.com/jokes/"
        private const val RANDOM = "random"
        private const val RANDOM_NUMBER = "random/"
        const val EXCLUDE_EXPLICIT = "explicit"


        const val NUMBER_OF_JOKES = 10


        //http://api.icndb.com/jokes/random?exclude=[nerdy]
        //http://api.icndb.com/jokes/random?exclude=[nerdy,explicit]
        //http://api.icndb.com/jokes/random
        //http://api.icndb.com/jokes/random/<number>
        //http://api.icndb.com/jokes/random?firstName=John&lastName=Doe
    }
}