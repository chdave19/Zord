package com.ytmusicclone.data.network.ktorclient.client

import com.ytmusicclone.data.model.TrackDto
import com.ytmusicclone.data.network.RemoteDiscoveryData
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking<Unit> {
    val scope1 = launch(Dispatchers.Default){
        for(i in 0..10)
            delay(1.seconds)
    }
    val scope2 = launch(Dispatchers.Default){
        while(scope1.isActive){
            println("is active")
        }
    }
    delay(5.seconds)
    scope1.cancel()
}

class KtorRemoteDiscoveryDataSource(private val httpClient: HttpClient) : RemoteDiscoveryData {

    override suspend fun getCoversAndRemixes(): List<TrackDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getYourDailyDiscover(): List<TrackDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getLongListen(): List<TrackDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getMusicVideoForYou(): List<TrackDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbumsForYou(): List<TrackDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getNewReleases(): List<TrackDto> {
        TODO("Not yet implemented")
    }
}