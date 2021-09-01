package com.siperi.service.data.source.local

import androidx.lifecycle.LiveData
import com.siperi.service.data.source.local.entity.CatalogEntity
import com.siperi.service.data.source.local.entity.EbookEntity
import com.siperi.service.data.source.local.room.CatalogDao

class LocalDataSource (private val mCatalogDao: CatalogDao) {

    fun getBooks(): LiveData<List<CatalogEntity>> = mCatalogDao.getBooks()

    fun getEbooks(): LiveData<List<EbookEntity>> = mCatalogDao.getEbooks()

    fun getBorrowBooks(): LiveData<List<CatalogEntity>> = mCatalogDao.getBorrowBook()

    fun insertBooks(books: List<CatalogEntity>) = mCatalogDao.insertBook(books)

    fun insertEbooks(books: List<EbookEntity>) = mCatalogDao.insertEbook(books)

    fun setBorrowBooks(books: CatalogEntity, newState: Boolean) {
        books.doBorrow = newState
        mCatalogDao.updateBorrowBook(books)
    }

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(catalogDao: CatalogDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(catalogDao)
            }
    }

}