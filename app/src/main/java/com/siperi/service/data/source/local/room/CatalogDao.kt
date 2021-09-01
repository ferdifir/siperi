package com.siperi.service.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.siperi.service.data.source.local.entity.CatalogEntity
import com.siperi.service.data.source.local.entity.EbookEntity

@Dao
interface CatalogDao {

    @Query("SELECT * FROM library")
    fun getBooks(): LiveData<List<CatalogEntity>>

    @Query("SELECT * FROM ebook")
    fun getEbooks(): LiveData<List<EbookEntity>>

    @Query("SELECT * FROM library where doBorrow = 1")
    fun getBorrowBook(): LiveData<List<CatalogEntity>>

    @Insert(onConflict = REPLACE)
    fun insertBook(books: List<CatalogEntity>)

    @Insert(onConflict = REPLACE)
    fun insertEbook(books: List<EbookEntity>)

    @Update
    fun updateBorrowBook(books: CatalogEntity)

}