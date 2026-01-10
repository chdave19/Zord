package com.ytmusicclone.data.network

import com.ytmusicclone.data.model.TrackDto


interface RemoteDiscoveryData{
    suspend fun getCoversAndRemixes(): List<TrackDto>
    suspend fun getYourDailyDiscover(): List<TrackDto>
    suspend fun getLongListen(): List<TrackDto>
    suspend fun getMusicVideoForYou(): List<TrackDto>
    suspend fun getAlbumsForYou(): List<TrackDto>
    suspend fun getNewReleases(): List<TrackDto>
}

interface RemotePersonalisationData{
    suspend fun getSpeedDial(trackId: Int?): TrackDto?
    suspend fun getMixedForYou(): List<TrackDto>
    suspend fun getForgottenFavourites(): List<TrackDto>
    suspend fun getQuickPicks(): List<TrackDto>
    suspend fun getFromYourLibrary(): List<TrackDto>
}