package com.ytmusicclone.data.network.ktorclient.client

import com.ytmusicclone.data.model.Music
import com.ytmusicclone.data.model.MusicDto
import com.ytmusicclone.data.network.RemotePersonalisationData
import io.ktor.client.HttpClient

class KtorRemotePersonalisationDataSource(private val httpClient: HttpClient): RemotePersonalisationData {
    override suspend fun getSpeedDial(): List<MusicDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getMixedForYou(): List<MusicDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getForgottenFavourites(): List<MusicDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getQuickPicks(): List<MusicDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getFromYourLibrary(): List<MusicDto> {
        TODO("Not yet implemented")
    }
}