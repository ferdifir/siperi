package com.siperi.service.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ebook(
    var title: String,
    var cover: String
) : Parcelable
