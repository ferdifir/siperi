package com.siperi.ui.slideshow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.siperi.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private lateinit var webViewModel: WebViewModel
    private var _binding: FragmentWebBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        webViewModel =
            ViewModelProvider(this).get(WebViewModel::class.java)

        _binding = FragmentWebBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setWebView()
        return root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        val webSettings = binding.wvWeb.settings
        webSettings.javaScriptEnabled = true
        binding.wvWeb.apply {
            loadUrl("https://belajar.kemdikbud.go.id/")
            webViewClient = WebViewClient()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}