package com.example.musicapi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SongItem (

        //place variables inside of a data class for ref.

        val artistName: String,
        val collectionName: String,
        val artworkUrl60: String,
        val trackPrice: Float,
        val previewUrl: String
): Parcelable
@Parcelize
data class ResultResponse(
        val resultCount: Int,
        val results: List<SongItem>
): Parcelable