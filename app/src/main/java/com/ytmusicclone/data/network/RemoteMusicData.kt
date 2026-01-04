package com.ytmusicclone.data.network

import com.ytmusicclone.data.model.MusicDto

interface RemoteDiscoveryData{
    suspend fun getCoversAndRemixes(): List<MusicDto>
    suspend fun getYourDailyDiscover(): List<MusicDto>
    suspend fun getLongListen(): List<MusicDto>
    suspend fun getMusicVideoForYou(): List<MusicDto>
    suspend fun getAlbumsForYou(): List<MusicDto>
    suspend fun getNewReleases(): List<MusicDto>
}

interface RemotePersonalisationData{
    suspend fun getSpeedDial(): List<MusicDto>
    suspend fun getMixedForYou(): List<MusicDto>
    suspend fun getForgottenFavourites(): List<MusicDto>
    suspend fun getQuickPicks(): List<MusicDto>
    suspend fun getFromYourLibrary(): List<MusicDto>
}