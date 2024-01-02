package com.annguyenhoang.fashiongallery.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.annguyenhoang.fashiongallery.adapter.BookAdapter
import com.annguyenhoang.fashiongallery.adapter.BookAdapterEvent
import com.annguyenhoang.fashiongallery.adapter.OnItemClicked
import com.annguyenhoang.fashiongallery.model.Book
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class BookFragment : TabFragment(R.layout.all_book_layout) {

    private var bookType: Int? = null

    private val bookAdapter by lazy {
        BookAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bookType = it.getInt(BOOK_TYPE)
        }
    }

    override fun viewsControl() {
        bookType?.let { typeBook ->
            val lstBook = when (typeBook) {
                1 -> Book.mock().filter { book -> (book.bookType?.id ?: 0) == typeBook }
                2 -> Book.mock().filter { book -> (book.bookType?.id ?: 0) == typeBook }
                else -> Book.mock()
            }

            bookAdapter.setBooksToList(lstBook)
        }
    }

    override fun initViews(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAdapterEventsSubscribe(bookAdapterEvent: BookAdapterEvent) {
        when (bookAdapterEvent) {
            is BookAdapterEvent.BookItemClicked -> {
                val bookSelected = bookAdapterEvent.bookClicked
                Log.d("ANDEBUGGGGG", "${bookSelected.name}")
            }
        }
    }

    companion object {
        const val BOOK_TYPE = "BOOK_TYPE"

        @JvmStatic
        fun newInstance(bookType: Int): BookFragment = BookFragment().apply {
            arguments = Bundle().apply {
                putInt(BOOK_TYPE, bookType)
            }
        }
    }

}