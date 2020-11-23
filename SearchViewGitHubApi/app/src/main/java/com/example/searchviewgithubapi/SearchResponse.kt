package com.example.searchviewgithubapi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
 The data classes works as objects where the data between the adapter and
 the Url query.
 */
@Parcelize
data class SearchResponse
    (
    val items: List<Users>
) : Parcelable

@Parcelize
data class Users
    (
    val login: String,
    val avatar_url: String,
    val created_at: String?,
    val email: String?,
    val location: String?,
    val followers: Int?,
    val following: Int?,
    val url: String,
    val score: String?,
    val bio: String?
) : Parcelable