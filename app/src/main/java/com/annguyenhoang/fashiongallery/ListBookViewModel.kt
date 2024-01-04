package com.annguyenhoang.fashiongallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.annguyenhoang.fashiongallery.enums.BookType
import com.annguyenhoang.fashiongallery.enums.FetchingStatus
import com.annguyenhoang.fashiongallery.model.Book
import com.annguyenhoang.fashiongallery.model.BooksRepoUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListBookViewModel : ViewModel() {
    private var _allBooks = MutableLiveData(BooksRepoUIState())
    val allBooks: LiveData<BooksRepoUIState> get() = _allBooks
    private var _wordBooks = MutableLiveData(BooksRepoUIState())
    val wordBooks: LiveData<BooksRepoUIState> get() = _wordBooks
    private var _imageBooks = MutableLiveData(BooksRepoUIState())
    val imageBooks: LiveData<BooksRepoUIState> get() = _imageBooks

    fun fetchAllBooksNew() = viewModelScope.launch {
        val data = withContext(Dispatchers.Default) {
            async {
                Book.mock().filter { it.bookType == BookType.WORD_BOOK }
            }.await()
        }

        withContext(Dispatchers.Main) {
            if (data.isNotEmpty()) {
                _allBooks.value = _allBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = data
                )
            }
        }
    }

    fun fetchAllBooks() = viewModelScope.launch {
        val data = withContext(Dispatchers.Default) {
            async {
                Book.mock()
            }.await()
        }

        withContext(Dispatchers.Main) {
            if (data.isNotEmpty()) {
                _allBooks.value = _allBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = data
                )
            }
        }
    }

    fun fetchWordBooks() = viewModelScope.launch {
        val data = withContext(Dispatchers.Default) {
            async {
                Book.mock().filter { it.bookType == BookType.WORD_BOOK }
            }.await()
        }

        withContext(Dispatchers.Main) {
            if (data.isNotEmpty()) {
                _wordBooks.value = _wordBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = data
                )
            }
        }
    }

    fun fetchImageBooks() = viewModelScope.launch {
        val data = withContext(Dispatchers.Default) {
            async {
                Book.mock().filter { it.bookType == BookType.PICTURE_BOOK }
            }.await()
        }

        withContext(Dispatchers.Main) {
            if (data.isNotEmpty()) {
                _imageBooks.value = _imageBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = data
                )
            }
        }
    }
}

