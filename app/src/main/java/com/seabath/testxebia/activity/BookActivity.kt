package com.seabath.testxebia.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

import com.seabath.testxebia.R
import com.seabath.testxebia.adapter.BookAdapter
import com.seabath.testxebia.model.Book
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class BookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        val book = intent.getParcelableExtra<Book>(BookAdapter.EXTRA_BOOK)

        val tvTitle: TextView = findViewById(R.id.title_book) as TextView
        tvTitle.text = book.title

        val tvPrice: TextView = findViewById(R.id.tv_price) as TextView
        tvPrice.text = String.format("%d %s", book.price, getString(R.string.currency))

        val tvSynopsis: TextView = findViewById(R.id.synopsis) as TextView
        val synopsis = StringBuilder()
        book.synopsis?.forEach {
            synopsis.append(it + "\n")
        }
        tvSynopsis.text = synopsis.toString()

        val ivCover: ImageView = findViewById(R.id.cover_book) as ImageView
        Picasso.with(this).load(book.cover).into(ivCover)
    }
}
