package com.siperi.service.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.siperi.service.data.source.local.entity.CatalogEntity
import com.siperi.service.data.source.local.entity.EbookEntity

@Database(
    entities = [CatalogEntity::class, EbookEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatalogDatabase: RoomDatabase() {

    abstract fun catalogDao(): CatalogDao

    companion object {

        @Volatile
        private var INSTANCE: CatalogDatabase? = null

        fun getInstance(context: Context): CatalogDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatalogDatabase::class.java,
                    "Catalog.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}