package com.siperi.ui.gallery

import androidx.lifecycle.ViewModel
import com.siperi.service.data.CatalogRepository

class EbookViewModel(catalogRepository: CatalogRepository) : ViewModel() {

    val catalog = catalogRepository.getEbooks()

}