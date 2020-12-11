package com.example.babershopcomms.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class personItem(

    var name: String? = " ",
    val adInfo: String? = " ",
    val setdate_time: String? = " ",
    val specBarb: String? = " ",
    val haircut: Boolean = false,
    val styles: Boolean = false,
    val braid_locs: Boolean = false,
    val trimming: Boolean = false,
    val color_dying: Boolean = false

): Parcelable

@Parcelize
data class CustomerResponse(
    val resultCount: Int,
    val result: List<personItem>
): Parcelable