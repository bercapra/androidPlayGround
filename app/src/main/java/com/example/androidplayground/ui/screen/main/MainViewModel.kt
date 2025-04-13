package com.example.androidplayground.ui.screen.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetCharacterListUseCase
import com.example.domain.utils.CoroutineResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
): ViewModel() {
    fun getCharacters() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            getCharacterListUseCase()
        }.let { result ->
            when(result){
                is CoroutineResult.Success -> {
                    Log.d("Characters List", result.data.toString())
                }
                is CoroutineResult.Failure -> {
                    Log.e("Characters ERROR", result.exception.toString())
                }
            }
        }
    }
}