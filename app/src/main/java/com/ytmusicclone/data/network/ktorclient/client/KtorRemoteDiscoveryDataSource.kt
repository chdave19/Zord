package com.ytmusicclone.data.network.ktorclient.client

import com.ytmusicclone.data.model.MusicDto
import com.ytmusicclone.data.network.RemoteDiscoveryData
import com.ytmusicclone.data.network.ktorclient.KtorClientProvider
import io.ktor.client.HttpClient
import io.ktor.client.plugins.expectSuccess
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

suspend fun main(){
    val client = KtorClientProvider.client
    val response = client.get("https://www.google.com"){
        expectSuccess = true
    }
    println(response.bodyAsText())
}

class KtorRemoteDiscoveryDataSource(private val httpClient: HttpClient) : RemoteDiscoveryData {

    override suspend fun getCoversAndRemixes(): List<MusicDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getYourDailyDiscover(): List<MusicDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getLongListen(): List<MusicDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getMusicVideoForYou(): List<MusicDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbumsForYou(): List<MusicDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getNewReleases(): List<MusicDto> {
        TODO("Not yet implemented")
    }
}