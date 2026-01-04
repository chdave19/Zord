package com.ytmusicclone.data.database

import com.ytmusicclone.data.model.MusicEntity
import kotlinx.coroutines.flow.Flow

interface DiscoveryLocalDataSource{
    fun getCoversAndRemixes(): Flow<List<MusicEntity>>
    fun getYourDailyDiscover(): Flow<List<MusicEntity>>
    fun getLongListen(): Flow<List<MusicEntity>>
    fun getMusicVideoForYou(): Flow<List<MusicEntity>>
    fun getAlbumsForYou(): Flow<List<MusicEntity>>
    fun getNewReleases(): Flow<List<MusicEntity>>
}

interface PersonalisationLocalDataSource{
    fun getSpeedDial(): Flow<List<MusicEntity>>
    fun getMixedForYou(): Flow<List<MusicEntity>>
    fun getForgottenFavourites(): Flow<List<MusicEntity>>
    fun getQuickPicks(): Flow<List<MusicEntity>>
    fun getFromYourLibrary(): Flow<List<MusicEntity>>
}