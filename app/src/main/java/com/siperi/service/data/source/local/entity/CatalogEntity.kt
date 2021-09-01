package com.siperi.service.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "library")
data class CatalogEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "bookImage")
    var imgPreview: String? = null,

    @ColumnInfo(name = "bookTitle")
    var title: String? = null,

    @ColumnInfo(name = "bookAuthor")
    var author: String? = null,

    @ColumnInfo(name = "bookCategory")
    var category: String? = null,

    @ColumnInfo(name = "bookPage")
    var page: String? = null,

    @ColumnInfo(name = "bookPublisher")
    var publisher: String? = null,

    @ColumnInfo(name = "bookIsbn")
    var isbn: String? = null,

    @ColumnInfo(name = "bookSummary")
    var summary: String? = null,

    @ColumnInfo(name = "doBorrow")
    var doBorrow: Boolean = false
) : Parcelable