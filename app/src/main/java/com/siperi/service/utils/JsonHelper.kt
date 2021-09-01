package com.siperi.service.utils

import android.content.Context
import com.siperi.R
import com.siperi.service.data.source.remote.response.CatalogResponse
import com.siperi.service.data.source.remote.response.EbookResponse
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileBookToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.catalog).bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun parsingFileEbookToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.ebook).bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun loadDataBook(): List<CatalogResponse> {
        val list = ArrayList<CatalogResponse>()
        val responseObject = JSONObject(parsingFileBookToString().toString())
        val listArray = responseObject.getJSONArray("places")
        for (i in 0 until listArray.length()) {
            val course = listArray.getJSONObject(i)

            val id = course.getInt("id")
            val image = course.getString("image")
            val title = course.getString("title")
            val author = course.getString("author")
            val category = course.getString("category")
            val page = course.getString("page")
            val publisher = course.getString("publisher")
            val isbn = course.getString("ISBN")
            val summary = course.getString("summary")

            val courseResponse = CatalogResponse(
                id = id,
                bookImage = image,
                title = title,
                author = author,
                category = category,
                page = page,
                publisher = publisher,
                isbn = isbn,
                summary = summary,
            )
            list.add(courseResponse)
        }
        return list
    }

    fun loadDataEbook(): List<EbookResponse> {
        val list = ArrayList<EbookResponse>()
        val responseObject = JSONObject(parsingFileEbookToString().toString())
        val listArray = responseObject.getJSONArray("places")
        for (i in 0 until listArray.length()) {
            val course = listArray.getJSONObject(i)

            val id = course.getInt("id")
            val image = course.getString("image")
            val title = course.getString("title")
            val url = course.getString("url")

            val courseResponse = EbookResponse(
                id = id,
                ebookImage = image,
                title = title,
                url = url
            )
            list.add(courseResponse)
        }
        return list
    }
}