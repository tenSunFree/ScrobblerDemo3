package com.example.scrobblerdemo3.home.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

sealed class HomeBaseEntity(
    @Ignore open val id: String,
    @Ignore open val name: String,
    @Ignore open val url: String,
    @Ignore open val imageUrl: String?
) : Parcelable {

    @Parcelize
    @Entity(tableName = "home")
    data class HomeEntity(
        val albumId: Int,
        val thumbnailUrl: String,
        val title: String,
        override val name: String = "",
        override val url: String = "",
        @PrimaryKey override val id: String,
        override val imageUrl: String? = null
    ) : HomeBaseEntity(id, name, url, imageUrl)
}