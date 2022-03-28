package com.example.jokeapplication.viewmodel

import androidx.lifecycle.*
import com.example.jokeapplication.rest.JokeRepository
import com.example.jokeapplication.utils.JokeState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class JokesViewModel @Inject constructor(
    private val jokeRepository: JokeRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    //variables to save user entered data from NameChangeFragment
    var userChoiceFirstName: String? = null
    var userChoiceLastName: String? = null

    private val _jokes : MutableLiveData<JokeState> =
        MutableLiveData(JokeState.DEFAULT)
    val jokes: LiveData<JokeState> get() = _jokes

    fun setLiveDataToDefault(){
        _jokes.postValue(JokeState.DEFAULT)
    }


    fun getRandomJoke(){
        _jokes.postValue(JokeState.LOADING)
        viewModelScope.launch(ioDispatcher){
            try{
                val response = jokeRepository.getRandomJoke()
                if(response.isSuccessful){
                    response.body()?.let {
                        //response not empty
                        _jokes.postValue(JokeState.SUCCESS(it))

                    }?: throw Exception("Response is null")
                }else{
                    throw Exception("No successful response")
                }

            }catch (e: Exception){
                //catch all errors
                _jokes.postValue(JokeState.ERROR(e))
            }
        }
    }

    fun getCleanRandomJoke(){
        _jokes.postValue(JokeState.LOADING)
        viewModelScope.launch(ioDispatcher){
            try{
                val response = jokeRepository.getExcludedRandomJoke()
                if(response.isSuccessful){
                    response.body()?.let {
                        //response not empty
                        _jokes.postValue(JokeState.SUCCESS(it))

                    }?: throw Exception("Response is null")
                }else{
                    throw Exception("No successful response")
                }

            }catch (e: Exception){
                //catch all errors
                _jokes.postValue(JokeState.ERROR(e))
            }
        }
    }

    fun getRandomJokeList(){
        _jokes.postValue(JokeState.LOADING)
        viewModelScope.launch(ioDispatcher){
            try{
                val response = jokeRepository.getNumberOfRandomJokes(20)
                if(response.isSuccessful){
                    response.body()?.let {
                        //response not empty
                        _jokes.postValue(JokeState.SUCCESS(it))

                    }?: throw Exception("Response is null")
                }else{
                    throw Exception("No successful response")
                }

            }catch (e: Exception){
                //catch all errors
                _jokes.postValue(JokeState.ERROR(e))
            }
        }
    }

    fun getRandomJokeWithName(){
        _jokes.postValue(JokeState.LOADING)
        viewModelScope.launch(ioDispatcher){
            try{
                val response = jokeRepository.getRandomJokeWithName(
                    userChoiceFirstName, userChoiceLastName)
                if(response.isSuccessful){
                    response.body()?.let {
                        //response not empty
                        _jokes.postValue(JokeState.SUCCESS(it))

                    }?: throw Exception("Response is null")
                }else{
                    throw Exception("No successful response")
                }

            }catch (e: Exception){
                //catch all errors
                _jokes.postValue(JokeState.ERROR(e))
            }
        }
    }

}

