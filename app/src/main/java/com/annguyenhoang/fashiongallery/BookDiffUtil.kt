package com.annguyenhoang.fashiongallery

import androidx.recyclerview.widget.DiffUtil
import com.annguyenhoang.fashiongallery.model.Book

class BookDiffUtil(
    private val oldBooks: MutableList<Book?>,
    private val newBooks: List<Book>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldBooks.size
    }

    override fun getNewListSize(): Int {
        return newBooks.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBooks[oldItemPosition]?.id == newBooks[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBooks[oldItemPosition] == newBooks[newItemPosition]
    }
}