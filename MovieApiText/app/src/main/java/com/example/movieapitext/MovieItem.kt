package com.example.movieapitext

import android.os.Parcel
import android.os.Parcelable

data class MovieItem(
    val title: String,
    val image: String,
    val rating: Double,
    val releaseYear: Int,
    val genre: List<String>
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "N/A",
            parcel.readString() ?: "N/A",
            parcel.readDouble(),
            parcel.readInt(),
            parcel.createStringArrayList()?.toList() ?: emptyList()) {
    }

    override fun describeContents(): Int {
        return Parcelable.CONTENTS_FILE_DESCRIPTOR
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest.let { parcel ->
            parcel?.writeString(title)
            parcel?.writeString(image)
            parcel?.writeDouble(rating)
            parcel?.writeInt(releaseYear)
            parcel?.writeList(genre)
        }
    }

    companion object CREATOR : Parcelable.Creator<MovieItem> {
        override fun createFromParcel(parcel: Parcel): MovieItem {
            return MovieItem(parcel)
        }

        override fun newArray(size: Int): Array<MovieItem?> {
            return arrayOfNulls(size)
        }
    }
}