package com.ytmusicclone.data.repository

import com.ytmusicclone.data.model.Track
import com.ytmusicclone.data.model.toDomain
import com.ytmusicclone.data.network.RemotePersonalisationData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonalisationRepository @Inject constructor(val remotePersonalisationData: RemotePersonalisationData) {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    val tracksFlow = MutableStateFlow(SpeedDialState())

    init {
        scope.launch(Dispatchers.Default){
            fetchRandomTracks()
        }
    }

    suspend fun fetchRandomTracks(){
        var tracks = mutableListOf<Track>()
        val tracksId = mutableSetOf<Int>()
        val tracksSet = mutableListOf<List<Track>>()
        var currentTrackListSize = 0
        val totalTracksToFetch = 27

        while(currentTrackListSize <= totalTracksToFetch){
            val track = remotePersonalisationData.getSpeedDial(null)
            if(track != null){
                if(!tracksId.contains(track.trackId)){
                    currentTrackListSize++
                    tracks.add(track.toDomain())
                    tracksId.add(track.trackId)
                    if(tracks.size >= 9){
                        tracksSet.add(tracks)
                        tracks = mutableListOf()
                    }
                }
            }
            delay(5)
        }
        tracksFlow.emit(SpeedDialState(true, tracksSet))
    }
}

data class SpeedDialState(
    val dataFetchingCompleted: Boolean = false,
    val trackList: List<List<Track>> = emptyList()
)