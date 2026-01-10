package com.ytmusicclone.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ytmusicclone.data.model.Track
import com.ytmusicclone.data.repository.PersonalisationRepository
import com.ytmusicclone.navigation.Destinations
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = HomeScreenViewModel.Factory::class)
class HomeScreenViewModel @AssistedInject constructor(
    @Assisted navKey: Destinations.Home,
    val personalisationRepository: PersonalisationRepository
) : ViewModel() {
    val tracks = personalisationRepository
        .tracksFlow
        .asStateFlow()
    val dataIsRefreshing = MutableStateFlow(false)

    fun refreshTracks(){
        dataIsRefreshing.value = true
        viewModelScope.cancel()
        viewModelScope.launch(Dispatchers.Default) {
            personalisationRepository.fetchRandomTracks()
        }.invokeOnCompletion {
            dataIsRefreshing.value = false
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(navKey: Destinations.Home): HomeScreenViewModel
    }
}