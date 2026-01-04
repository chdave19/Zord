package com.ytmusicclone.data.model

import android.R
import androidx.compose.runtime.Immutable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Immutable
data class MusicDto @OptIn(ExperimentalUuidApi::class) constructor(
    val songName: String,
    val singerName: String,
    val albumUri: String,
    val id: String = Uuid.random().toString()
)

fun MusicDto.toDomain() = Music(
    songName,
    singerName,
    albumUri,
    id
)

fun MusicDto.toEntity() = MusicEntity(
    songName,
    singerName,
    albumUri,
    id
)