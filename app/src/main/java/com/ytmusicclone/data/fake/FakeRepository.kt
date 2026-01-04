package com.ytmusicclone.data.fake

class FakeRepository(private val fakeDataSource: FakeLocalDataSource) {
    fun updatedSpeedDialSongs() = fakeDataSource.getSpeedDial()
}