package com.ytmusicclone.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TrackDto(
    @SerialName("id") val trackId: Int,
    @SerialName("title") val songName: String,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("preview") val preview: String? = null,
    @SerialName("artist") val artist: Artist? = null,
    @SerialName("album") val album: Album? = null
)

@Serializable
data class Artist(
    @SerialName("id") val artistId: Int,
    @SerialName("name") val artistName: String,
    @SerialName("picture") val picture: String,
    @SerialName("picture_small") val smallPicture: String,
    @SerialName("picture_big") val bigPicture: String,
    @SerialName("picture_xl") val pictureXl: String
)

@Serializable
data class Album(
    @SerialName("id") val albumId: Int,
    @SerialName("title") val albumTitle: String,
    @SerialName("cover") val albumCover: String,
    @SerialName("cover_big") val albumCoverBig: String,
    @SerialName("cover_xl") val albumCoverXl: String,
    @SerialName("release_date") val releaseDate: String
)

fun TrackDto.toDomain() = Track(
    songName,
    artist?.artistName,
    album?.albumCover,
    album?.albumCoverBig,
    album?.albumCoverXl,
    releaseDate,
    trackId
)

//fun MusicDto.toEntity() = MusicEntity(
//    songName,
//    singerName,
//    albumUri,
//)