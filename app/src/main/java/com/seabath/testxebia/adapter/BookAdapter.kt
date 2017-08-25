package com.seabath.testxebia.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.seabath.testxebia.model.Book

class BookAdapter(var mBooks: List<Book>) : BaseAdapter() {

    override fun getItem(position: Int): Any = mBooks.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mBooks.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
            convertView!!

}
