package com.ytmusicclone.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ytmusicclone.data.fake.FakeLocalDataSource
import com.ytmusicclone.data.fake.FakeRepository
import com.ytmusicclone.data.model.toDomain
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SpeedDialViewModel : ViewModel() {
    val musicData = Database
        .fakeRepository
        .updatedSpeedDialSongs()
        .map { list ->
            list.take(9) //Only take 9 items from the list
        }
        .map { list ->
            list.map { entity ->
                entity.toDomain()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), emptyList()
        )
}

object Database { //No dependency injection in the app yet, so we inject manually
    val fakeRepository by lazy {
        FakeRepository(FakeLocalDataSource())
    }
}