package com.siperi.service.data.source.remote.response

data class CatalogResponse(
    var id: Int,
    var bookImage: String,
    var title: String,
    var author: String,
    var category: String,
    var page: String,
    var publisher: String,
    var isbn: String,
    var summary: String,
    var doBorrow: Boolean = false
)
