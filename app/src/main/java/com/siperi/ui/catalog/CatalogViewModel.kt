package com.siperi.ui.catalog

import androidx.lifecycle.ViewModel
import com.siperi.service.data.CatalogRepository

class CatalogViewModel(catalogRepository: CatalogRepository) : ViewModel() {

    val catalog = catalogRepository.getBooks()
}