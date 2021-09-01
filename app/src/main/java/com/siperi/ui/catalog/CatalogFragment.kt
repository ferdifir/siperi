package com.siperi.ui.catalog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.siperi.databinding.FragmentCatalogBinding
import com.siperi.service.data.Resource
import com.siperi.service.ui.CatalogAdapter
import com.siperi.service.ui.ViewModelFactory
import com.siperi.ui.detail.DetailEbookActivity

class CatalogFragment : Fragment() {

    private lateinit var catalogViewModel: CatalogViewModel
    private var _binding: FragmentCatalogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val catalogAdapter = CatalogAdapter()
            catalogAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailEbookActivity::class.java)
                intent.putExtra(DetailEbookActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            catalogViewModel = ViewModelProvider(this, factory)[CatalogViewModel::class.java]

            catalogViewModel.catalog.observe(viewLifecycleOwner, { book ->
                if (book != null) {
                    when(book) {
                        is Resource.Loading -> binding.pbCatalog.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pbCatalog.visibility = View.GONE
                            catalogAdapter.setData(book.data)
                        }
                        is Resource.Error -> binding.pbCatalog.visibility = View.VISIBLE
                    }
                }
            })

            binding.rvCatalog.apply {
                layoutManager = GridLayoutManager(
                    context,
                    2,
                    VERTICAL,
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