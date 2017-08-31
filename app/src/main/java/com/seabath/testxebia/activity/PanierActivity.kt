package com.seabath.testxebia.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

import com.seabath.testxebia.R
import com.seabath.testxebia.adapter.BookAdapter
import com.seabath.testxebia.adapter.BookPanierAdapter
import com.seabath.testxebia.model.Book

class PanierActivity : AppCompatActivity() {

    var mListViewBook: ListView? = null
    var mListBook: List<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panier)

        mListBook = intent.getParcelableArrayExtra(MainActivity.Static.EXTRA_LIST_BOOK).toList() as List<Book>
        mListViewBook = findViewById(R.id.lv_book_list) as ListView?
        mListViewBook?.adapter = BookPanierAdapter(this, mListBook!!)
    }
}
