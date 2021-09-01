package com.siperi.service.di

import android.content.Context
import com.siperi.service.data.CatalogRepository
import com.siperi.service.data.source.local.LocalDataSource
import com.siperi.service.data.source.local.room.CatalogDatabase
import com.siperi.service.data.source.remote.RemoteDataSource
import com.siperi.service.utils.AppExecutors
import com.siperi.service.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): CatalogRepository {
        val database = CatalogDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.catalogDao())
        val appExecutors = AppExecutors()

        return CatalogRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
