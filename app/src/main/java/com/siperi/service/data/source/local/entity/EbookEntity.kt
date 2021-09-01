package com.siperi.service.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "ebook")
data class EbookEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "ebookImage")
    var image: String? = null,

    @ColumnInfo(name = "ebookTitle")
    var title: String? = null,

    @ColumnInfo(name = "ebookUrl")
    var url: String? = null

) : Parcelable
