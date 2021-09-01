package com.siperi.service.data

import androidx.lifecycle.LiveData
import com.siperi.service.data.source.local.LocalDataSource
import com.siperi.service.data.source.local.entity.CatalogEntity
import com.siperi.service.data.source.local.entity.EbookEntity
import com.siperi.service.data.source.remote.RemoteDataSource
import com.siperi.service.data.source.remote.network.ApiResponse
import com.siperi.service.data.source.remote.response.CatalogResponse
import com.siperi.service.data.source.remote.response.EbookResponse
import com.siperi.service.utils.AppExecutors
import com.siperi.service.utils.DataMapper

class CatalogRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
){

    fun getBooks(): LiveData<Resource<List<CatalogEntity>>> =
        object : NetworkBoundResource<List<CatalogEntity>, List<CatalogResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<CatalogEntity>> {
                return localDataSource.getBooks()
            }

            override fun shouldFetch(data: List<CatalogEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CatalogResponse>>> =
                remoteDataSource.getBooks()

            override fun saveCallResult(data: List<CatalogResponse>) {
                val bookList = DataMapper.mapBookResponsesToEntities(data)
                localDataSource.insertBooks(bookList)
            }
        }.asLiveData()

    fun getEbooks(): LiveData<Resource<List<EbookEntity>>> =
        object : NetworkBoundResource<List<EbookEntity>, List<EbookResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<EbookEntity>> {
                return localDataSource.getEbooks()
            }

            override fun shouldFetch(data: List<EbookEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<EbookResponse>>> =
                remoteDataSource.getEbooks()

            override fun saveCallResult(data: List<EbookResponse>) {
                val bookList = DataMapper.mapEbookResponsesToEntities(data)
                localDataSource.insertEbooks(bookList)
            }
        }.asLiveData()

    fun getBorrowBook(): LiveData<List<CatalogEntity>> {
        return localDataSource.getBorrowBooks()
    }

    fun setBorrowBook(catalog: CatalogEntity, state: Boolean) {
        appExecutors.diskIO().execute{ localDataSource.setBorrowBooks(catalog, state) }
    }

    companion object {
        @Volatile
        private var instance: CatalogRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(remoteData, localData, appExecutors)
            }
    }
}