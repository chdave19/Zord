package com.ytmusicclone.data.fake

import android.content.Context
import android.util.Log
import com.ytmusicclone.data.database.DiscoveryLocalDataSource
import com.ytmusicclone.data.database.PersonalisationLocalDataSource
import com.ytmusicclone.data.model.MusicEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class FakeLocalDataSource : DiscoveryLocalDataSource, PersonalisationLocalDataSource {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    val albumUrls = listOf(
        "https://images.pexels.com/photos/34917999/pexels-photo-34917999.jpeg",
        "https://images.pexels.com/photos/1024975/pexels-photo-1024975.jpeg",
        "https://images.pexels.com/photos/1535244/pexels-photo-1535244.jpeg",
        "https://images.pexels.com/photos/1687845/pexels-photo-1687845.jpeg",
        "https://images.pexels.com/photos/815880/pexels-photo-815880.jpeg",
        "https://images.pexels.com/photos/1718345/pexels-photo-1718345.jpeg",
        "https://images.pexels.com/photos/18545705/pexels-photo-18545705.jpeg"
    )
    private val _updatedSpeedDialFlow: MutableStateFlow<List<MusicEntity>> //The single source of truth for all client that is using the SpeedDial data
    init {
        _updatedSpeedDialFlow = MutableStateFlow(getFreshSpeedDialData(24))
        scope.launch {
            withContext(Dispatchers.Default) {//Ensure it runs on the background
                while (true) {
                    delay(10.seconds) //update the current data to simulate database refresh
                    _updatedSpeedDialFlow.emit(getFreshSpeedDialData(24))
                }
            }
        }
    }

    fun getFreshSpeedDialData(size: Int): List<MusicEntity>{
        return List(size) {
            MusicEntity(
                songName = "Zero",
                singerName = "Chris Brown",
                albumUri = albumUrls[Random.nextInt(albumUrls.size)]
            )
        }
    }

    override fun getCoversAndRemixes(): Flow<List<MusicEntity>> {
        TODO("Not yet implemented")
    }

    override fun getYourDailyDiscover(): Flow<List<MusicEntity>> {
        TODO("Not yet implemented")
    }

    override fun getLongListen(): Flow<List<MusicEntity>> {
        TODO("Not yet implemented")
    }

    override fun getMusicVideoForYou(): Flow<List<MusicEntity>> {
        TODO("Not yet implemented")
    }

    override fun getAlbumsForYou(): Flow<List<MusicEntity>> {
        TODO("Not yet implemented")
    }

    override fun getNewReleases(): Flow<List<MusicEntity>> {
        TODO("Not yet implemented")
    }

    override fun getSpeedDial(): Flow<List<MusicEntity>> {
        return _updatedSpeedDialFlow
    }

    override fun getMixedForYou(): Flow<List<MusicEntity>> {
        TODO("Not yet implemented")
    }

    override fun getForgottenFavourites(): Flow<List<MusicEntity>> {
        TODO("Not yet implemented")
    }

    override fun getQuickPicks(): Flow<List<MusicEntity>> {
        TODO("Not yet implemented")
    }

    override fun getFromYourLibrary(): Flow<List<MusicEntity>> {
        TODO("Not yet implemented")
    }
}