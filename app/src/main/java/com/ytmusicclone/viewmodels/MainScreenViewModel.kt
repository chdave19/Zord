package com.ytmusicclone.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ytmusicclone.data.fake.FakeLocalDataSource
import com.ytmusicclone.data.fake.FakeRepository
import com.ytmusicclone.data.model.toDomain
import com.ytmusicclone.navigation.Destinations
import com.ytmusicclone.utility.android.NetworkMonitor
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel(assistedFactory = MainScreenViewModel.Factory::class)
class MainScreenViewModel @AssistedInject constructor(
    @Assisted navKey: Destinations.MainScreen,
    networkMonitor: NetworkMonitor
) : ViewModel() {
    private val manuallyCloseDialog = MutableStateFlow(false)
    val showDialog = combine(
        networkMonitor.observeNetworkConnectivityStatus,
        manuallyCloseDialog
    ) { netWorkAvailable, dialogClosed ->
        when{
            (!netWorkAvailable && !dialogClosed) -> true
            (netWorkAvailable) ->{
                manuallyCloseDialog.value = false
                false
            }
            else -> false
        }
    }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), false
        )

    fun closeDialog(){
        manuallyCloseDialog.value = true
    }

    @AssistedFactory
    interface Factory {
        fun create(navKey: Destinations.MainScreen): MainScreenViewModel
    }
}