package com.siperi.ui.detail

import androidx.lifecycle.ViewModel
import com.siperi.service.data.CatalogRepository

class DetailBookViewModel(catalogRepository: CatalogRepository) : ViewModel() {

    val book = catalogRepository.getBooks()
}