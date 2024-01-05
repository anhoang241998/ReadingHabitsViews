package com.annguyenhoang.fashiongallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.enums.ItemType
import com.annguyenhoang.fashiongallery.R
import com.annguyenhoang.fashiongallery.model.Book2

class Books2Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    override fun submitList(list: MutableList<ItemRecyclerView>?) {
//        super.submitList(list?.let { ArrayList(it) })
//    }

    private val listData = mutableListOf<Book2>()

    fun addDataToList(listData: List<Book2>) {
        if (this.listData.isNotEmpty()) {
            this.listData.clear()
        }

        this.listData.addAll(listData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = when (viewType) {
            ItemType.BOOK.viewType -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_book_test, parent, false)
                BookViewHolder(view)
            }

            else -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_author, parent, false)

                AuthorViewHolder(view)
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemRecyclerView = listData[position]

        when (itemRecyclerView.viewType) {
            ItemType.BOOK -> {
                (holder as BookViewHolder).bind(itemRecyclerView)
            }

            ItemType.AUTHOR -> {
                (holder as AuthorViewHolder).bind(itemRecyclerView)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].viewType.viewType
    }

    override fun getItemCount() = listData.size

    inner class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvBookName = view.findViewById<TextView>(R.id.test)

        fun bind(book: Book2) {
            tvBookName.text = book.bookName
        }
    }

    inner class AuthorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvAuthor = view.findViewById<TextView>(R.id.author)

        fun bind(author: Book2) {
            tvAuthor.text = author.authorName
        }

    }

//    companion object {
//        val ITEM_VIEW_DIFFUTIL = object : DiffUtil.ItemCallback<ItemRecyclerView>() {
//            override fun areItemsTheSame(
//                oldItem: ItemRecyclerView,
//                newItem: ItemRecyclerView
//            ): Boolean {
//                return oldItem.id == newItem.id
//            }
//
//            override fun areContentsTheSame(
//                oldItem: ItemRecyclerView,
//                newItem: ItemRecyclerView
//            ): Boolean {
//                return oldItem.id == newItem.id && oldItem.viewType == newItem.viewType
//            }
//
//        }
//    }
}
