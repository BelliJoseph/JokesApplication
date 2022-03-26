package com.example.jokeapplication.di

import com.example.jokeapplication.rest.JokeRepository
import com.example.jokeapplication.rest.JokeAPI
import com.example.jokeapplication.rest.JokesRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun providesGson() = Gson()

    @Provides
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providesRetrofitService(okHttpClient: OkHttpClient, gson: Gson): JokeAPI{
        return Retrofit.Builder()
            .baseUrl(JokeAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(JokeAPI::class.java)
    }

    @Provides
    fun providesJokeRepository(jokeAPI: JokeAPI): JokeRepository =
        JokesRepositoryImpl(jokeAPI)

}