package com.siperi.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Rencananya : diisi website belajar dari kemendikbud/channel YT/Catatan"
    }
    val text: LiveData<String> = _text
}