package com.siperi.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.siperi.databinding.ActivityWebViewBinding
import com.siperi.service.data.source.local.entity.EbookEntity

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailEBook = intent.getParcelableExtra<EbookEntity>(DATA_URL)
        setWebView(detailEBook)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView(detailEBook: EbookEntity?) {
        val webSettings = binding.wvEbook.settings
        webSettings.javaScriptEnabled = true
        detailEBook?.let {
            binding.wvEbook.apply {
                detailEBook.url?.let { it1 -> loadUrl(it1) }
                webViewClient = WebViewClient()
            }
        }
    }

    companion object {
        const val DATA_URL = "data_url"
    }
}