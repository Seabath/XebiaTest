package com.seabath.testxebia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.seabath.testxebia.R
import com.seabath.testxebia.model.Book

class BookAdapter(context: Context, var mBooks: List<Book>) : BaseAdapter() {

    private val mInflator : LayoutInflater = LayoutInflater.from(context)

    override fun getItem(position: Int): Any = mBooks.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mBooks.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view : View?
        val vh : CellBookRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.cell_book, parent, false)
            vh = CellBookRowHolder(view)
        } else {
            view = convertView
            vh = view.tag as CellBookRowHolder
        }

        vh.title.text = mBooks[position].title
        return view
    }


    private class CellBookRowHolder(row: View?) {
        public val title: TextView
        init {
            this.title = row?.findViewById(R.id.title_book) as TextView
        }
    }
}
