package com.annguyenhoang.fashiongallery.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.ListBookViewModel
import com.annguyenhoang.fashiongallery.R
import com.annguyenhoang.fashiongallery.adapter.BookAdapter
import com.annguyenhoang.fashiongallery.adapter.BookAdapterEvent
import com.annguyenhoang.fashiongallery.enums.FetchingStatus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class WordBookFragment : TabFragment(R.layout.recycleview_book_layout) {

    private val viewModel: ListBookViewModel by activityViewModels()
    private val bookAdapter by lazy {
        BookAdapter()
    }

    override fun viewsControl() {
        viewModel.allBooks.observe(viewLifecycleOwner) { uiState ->
            when (uiState.fetchingStatus) {
                FetchingStatus.SUCCESS -> {
                    uiState?.data?.let { dataBooks ->
                        bookAdapter.setBooksToList(dataBooks)
                    }
                }

                else -> {}
            }
        }

        viewModel.fetchAllBooks()
    }

    override fun initViews(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
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
}