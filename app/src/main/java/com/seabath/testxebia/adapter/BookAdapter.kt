package com.seabath.testxebia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.seabath.testxebia.R
import com.seabath.testxebia.model.Book
import com.squareup.picasso.Picasso

class BookAdapter(var mContext: Context, var mBooks: List<Book>, var mHasPanier: Boolean) : BaseAdapter() {

    var mPanier: MutableList<Book> = mutableListOf()

    private val mInflator: LayoutInflater = LayoutInflater.from(mContext)

    override fun getItem(position: Int): Any = mBooks.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mBooks.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View
        val vh: CellBookRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.cell_book, parent, false)
            vh = CellBookRowHolder(view)
        } else {
            view = convertView
            vh = CellBookRowHolder(view)
        }
        val book = mBooks[position];

        vh.title.text = book.title
        Picasso.with(mContext).load(book.cover).into(vh.cover)
        if (mHasPanier) {
            vh.buttonAdd.visibility = View.VISIBLE
            vh.buttonAdd.setOnClickListener(View.OnClickListener {
                mPanier.add(book)
            })
        }
        vh.price.text = String.format("%d%s", book.price, R.string.currency)
        return view
    }

    fun getPanierSize(): Int = mPanier.size


    private class CellBookRowHolder(row: View?) {
        val cover: ImageView
        val title: TextView
        var buttonAdd: Button
        val price: TextView

        init {
            this.title = row?.findViewById(R.id.title_book) as TextView
            this.cover = row.findViewById(R.id.cover_book) as ImageView
            this.price = row.findViewById(R.id.tv_price) as TextView
            this.buttonAdd = row.findViewById(R.id.btn_add) as Button
        }
    }
}
