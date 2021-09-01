package com.siperi.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.siperi.databinding.FragmentEbookBinding
import com.siperi.service.data.Resource
import com.siperi.service.ui.EbookAdapter
import com.siperi.service.ui.ViewModelFactory
import com.siperi.ui.webview.WebViewActivity
import com.siperi.ui.webview.WebViewActivity.Companion.DATA_URL

class EbookFragment : Fragment() {

    private lateinit var ebookViewModel: EbookViewModel
    private var _binding: FragmentEbookBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEbookBinding.inflate(inflater, container, false)
        
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val catalogAdapter = EbookAdapter()
            catalogAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, WebViewActivity::class.java)
                intent.putExtra(DATA_URL, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            ebookViewModel = ViewModelProvider(this, factory)[EbookViewModel::class.java]

            ebookViewModel.catalog.observe(viewLifecycleOwner, { book ->
                if (book != null) {
                    when(book) {
                        is Resource.Loading -> binding.pbEbook.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pbEbook.visibility = View.GONE
                            catalogAdapter.setData(book.data)
                        }
                        is Resource.Error -> binding.pbEbook.visibility = View.VISIBLE
                    }
                }
            })

            binding.rvEbook.apply {
                layoutManager = GridLayoutManager(
                    context,
                    2,
                    LinearLayoutManager.VERTICAL,
                    false)
                setHasFixedSize(true)
                adapter = catalogAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}