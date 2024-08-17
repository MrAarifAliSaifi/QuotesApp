package com.example.quotesapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quotesapplication.databinding.QuotesActivityBinding
import com.example.quotesapplication.model.Quote

class QuotesActivity : AppCompatActivity() {
    private lateinit var binding: QuotesActivityBinding
    private lateinit var viewModel: QuotesVM
    private lateinit var quotesText:TextView
    private lateinit var quotesTextAuthor:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= QuotesActivityBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        viewModel = ViewModelProvider(this, VMFactory(this))[QuotesVM::class.java]
        quotesText=binding.quoteText
        quotesTextAuthor=binding.quoteAuthor
        setQuotes(viewModel.getQuote())
        binding.apply {
            btnNext.setOnClickListener {
                onNext()
                btnPreviou.setOnClickListener {
                    onPrevious()
                }
                floatingActionButton.setOnClickListener {
                    onShareClicked()
                }
            }
        }
    }
   private fun setQuotes(quote:Quote){
        quotesText.text = quote.text
        quotesTextAuthor.text = quote.author
    }

    private fun onPrevious() {
        setQuotes(viewModel.previousQuote())
    }

   private fun onNext() {
        setQuotes(viewModel.nextQuote())
    }
   private fun onShareClicked(){
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, viewModel.getQuote().text)
            startActivity(intent)
       Toast.makeText(this, "Here is the clicked happens in the system", Toast.LENGTH_SHORT).show()
    }
}