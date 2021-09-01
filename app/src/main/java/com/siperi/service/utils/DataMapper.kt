package com.siperi.service.utils

import com.siperi.service.data.source.local.entity.CatalogEntity
import com.siperi.service.data.source.local.entity.EbookEntity
import com.siperi.service.data.source.remote.response.CatalogResponse
import com.siperi.service.data.source.remote.response.EbookResponse

object DataMapper {
    fun mapBookResponsesToEntities(input: List<CatalogResponse>): List<CatalogEntity> {
        val bookList = ArrayList<CatalogEntity>()
        input.map {
            val catalog = CatalogEntity(
                id = it.id,
                imgPreview = it.bookImage,
                title = it.title,
                author = it.author,
                category = it.category,
                page = it.page,
                publisher = it.publisher,
                isbn = it.isbn,
                summary = it.summary,
                doBorrow = false
            )
            bookList.add(catalog)
        }
        return bookList
    }

    fun mapEbookResponsesToEntities(input: List<EbookResponse>): List<EbookEntity> {
        val bookList = ArrayList<EbookEntity>()
        input.map {
            val catalog = EbookEntity(
                id = it.id,
                image = it.ebookImage,
                title = it.title,
                url = it.url
            )
            bookList.add(catalog)
        }
        return bookList
    }
}