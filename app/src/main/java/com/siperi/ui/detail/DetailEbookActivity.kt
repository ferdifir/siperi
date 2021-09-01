package com.siperi.ui.detail

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.siperi.R
import com.siperi.databinding.ActivityDetailBinding
import com.siperi.service.data.source.local.entity.CatalogEntity
import com.siperi.service.ui.ViewModelFactory

class DetailEbookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailBookViewModel: DetailBookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailBookViewModel = ViewModelProvider(this, factory)[DetailBookViewModel::class.java]

        val detailBook = intent.getParcelableExtra<CatalogEntity>(EXTRA_DATA)
        showDetailBook(detailBook)
    }

    private fun showDetailBook(detailBook: CatalogEntity?) {
        detailBook?.let {
            with(binding) {
                tvTitle.text = detailBook.title
                tvAuthor.text = detailBook.author
                tvCategory.text = detailBook.category
                tvIsbn.text = detailBook.isbn
                tvPageNumber.text = detailBook.page
                tvPublisher.text = detailBook.publisher
                tvSummary.text = detailBook.summary

                Glide.with(this@DetailEbookActivity)
                    .load(detailBook.imgPreview)
                    .into(ivCoverBook)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail, menu)
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}