package com.siperi.service.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.siperi.service.data.CatalogRepository
import com.siperi.service.di.Injection
import com.siperi.ui.detail.DetailBookViewModel
import com.siperi.ui.catalog.CatalogViewModel
import com.siperi.ui.gallery.EbookViewModel

class ViewModelFactory private constructor(private val catalogRepository: CatalogRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance
                ?: synchronized(this) {
                instance
                    ?: ViewModelFactory(
                        Injection.provideRepository(
                            context
                        )
                    )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(CatalogViewModel::class.java) -> {
                CatalogViewModel(catalogRepository) as T
            }
            modelClass.isAssignableFrom(DetailBookViewModel::class.java) -> {
                DetailBookViewModel(catalogRepository) as T
            }
            modelClass.isAssignableFrom(EbookViewModel::class.java) -> {
                EbookViewModel(catalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}