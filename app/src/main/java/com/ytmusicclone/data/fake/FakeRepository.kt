package com.ytmusicclone.data.fake

import com.ytmusicclone.data.database.PersonalisationLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FakeRepository @Inject constructor(private val fakeDataSource: PersonalisationLocalDataSource) {
    fun updatedSpeedDialSongs() = fakeDataSource.getSpeedDial()
}