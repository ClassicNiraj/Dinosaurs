package com.example.dinosaurs.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinosaurs.ui.theme.network.DinoApi
import com.example.dinosaurs.ui.theme.network.DinoPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DinoUiState {
    data class Success(val photos: List<DinoPhoto>) : DinoUiState
    object Error : DinoUiState
    object Loading : DinoUiState
}

class DinoViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var  dinoUiState: DinoUiState by mutableStateOf(DinoUiState.Loading)
        private set

    /**
     * Call getDinoPhotos() on init so we can display status immediately.
     */
    init {
        getDinoPhotos()
    }

    /**
     * Gets Mars photos information from the Dino API Retrofit service and updates
     * [DinoPhoto] [List] [MutableList].
     */
    fun getDinoPhotos() {
        viewModelScope.launch {
            try {
                DinoUiState.Success(DinoApi.retrofitService.getPhotos())
            } catch(e: IOException){
                DinoUiState.Error
            }
        }
    }
}