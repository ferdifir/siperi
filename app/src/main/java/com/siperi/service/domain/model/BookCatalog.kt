package com.siperi.service.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookCatalog(
    var id: Int = 0,
    var bookImage: String? = null,
    var title: String? = null,
    var author: String? = null,
    var category: String? = null,
    var page: String? = null,
    var publisher: String? = null,
    var summary: String? = null,
) : Parcelable
