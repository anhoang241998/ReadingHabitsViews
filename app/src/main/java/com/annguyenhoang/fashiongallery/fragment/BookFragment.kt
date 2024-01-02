package com.annguyenhoang.fashiongallery.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.annguyenhoang.fashiongallery.adapter.BookAdapter
import com.annguyenhoang.fashiongallery.model.Book

class BookFragment(val typeBook: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.all_book_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lstBook = when (typeBook) {
            1 -> Book.mock().filter { book -> (book.bookType?.id ?: 0) == typeBook }
            2 -> Book.mock().filter { book -> (book.bookType?.id ?: 0) == typeBook }
            else -> Book.mock()
        }

        val bookAdapter = BookAdapter(lstBook)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = bookAdapter
    }
}