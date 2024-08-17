package com.example.quotesapplication

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quotesapplication.model.Quote
import com.google.gson.Gson

class QuotesVM(val context:Context):ViewModel() {
        private var quoteList: Array<Quote> = emptyArray()
        private var index = 0

        init {
            quoteList = loadQuoteFromAssets()
        }

        private fun loadQuoteFromAssets(): Array<Quote> {
            val inputStream = context.assets.open("quotes.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charsets.UTF_8)
            val gson = Gson()
            return gson.fromJson(json, Array<Quote>::class.java)
        }

        fun getQuote() = quoteList[index]
        // we are getting the quotes from the system in the case of quotes.

        fun getQuotes()= quoteList[index]
        // we are the system of the
        fun nextQuote() = quoteList[++index % quoteList.size]

        fun previousQuote() = quoteList[(--index + quoteList.size) % quoteList.size]
}