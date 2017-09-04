package com.seabath.testxebia.model

import android.os.Parcel
import android.os.Parcelable

class Book() : Parcelable {
    var isbn: String? = null
    var title: String? = null
    var price: Int = 0
    var cover: String? = null
    var synopsis: List<String>? = null

    constructor(parcel: Parcel) : this() {
        isbn = parcel.readString()
        title = parcel.readString()
        price = parcel.readInt()
        cover = parcel.readString()
        synopsis = parcel.createStringArrayList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(isbn)
        parcel.writeString(title)
        parcel.writeInt(price)
        parcel.writeString(cover)
        parcel.writeStringList(synopsis)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book = Book(parcel)

        override fun newArray(size: Int): Array<Book?> = arrayOfNulls(size)
    }
}