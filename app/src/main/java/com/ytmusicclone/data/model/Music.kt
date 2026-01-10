package com.ytmusicclone.data.model

import android.R
import androidx.compose.runtime.Immutable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Immutable
data class Track (
    val trackName: String = "",
    val artistName: String? = null,
    val trackCover: String? = null,
    val trackCoverBig: String? = null,
    val trackCoverXl: String? = null,
    val releaseDate: String? = null,
    val trackId: Int = 0,
)