package com.ytmusicclone.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ytmusicclone.data.fake.FakeLocalDataSource
import com.ytmusicclone.data.fake.FakeRepository
import com.ytmusicclone.data.model.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SpeedDialViewModel @Inject constructor(fakeRepository: FakeRepository) : ViewModel() {

}