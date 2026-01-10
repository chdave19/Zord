package com.ytmusicclone.data.network.ktorclient.client

import com.ytmusicclone.data.model.TrackDto
import com.ytmusicclone.data.network.RemotePersonalisationData
import com.ytmusicclone.data.network.ktorclient.KtorClientProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import javax.inject.Inject
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class KtorRemotePersonalisationDataSource @Inject constructor(private val httpClientProvider: KtorClientProvider): RemotePersonalisationData {
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun getSpeedDial(trackId: Int?): TrackDto?{
        var response: HttpResponse? = null
        if(trackId == null){
            val randomTrackId = Random.nextInt(1000000)
            response = httpClientProvider.client.get("https://api.deezer.com/track/$randomTrackId"){
                method = HttpMethod.Get
            }
        }else{
            response = httpClientProvider.client.get("https://api.deezer.com/track/$trackId"){
                method = HttpMethod.Get
            }
        }
        val raw = response.bodyAsText()
        val json = Json { ignoreUnknownKeys = true }
        val element = json.parseToJsonElement(raw)
        if("error" in element.jsonObject){
            return null
        }else{
            val track = json.decodeFromJsonElement<TrackDto>(element)
            return track
        }
    }

    override suspend fun getMixedForYou(): List<TrackDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getForgottenFavourites(): List<TrackDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getQuickPicks(): List<TrackDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getFromYourLibrary(): List<TrackDto> {
        TODO("Not yet implemented")
    }
}