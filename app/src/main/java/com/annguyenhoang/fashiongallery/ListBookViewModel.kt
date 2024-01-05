package com.annguyenhoang.fashiongallery

import android.util.Log
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class ListBookViewModel : ViewModel() {
    private var _allBooks = MutableLiveData(BooksRepoUIState())
    val allBooks: LiveData<BooksRepoUIState> get() = _allBooks

    private var _wordBooks = MutableLiveData(BooksRepoUIState())
    val wordBooks: LiveData<BooksRepoUIState> get() = _wordBooks

    private var _imageBooks = MutableLiveData(BooksRepoUIState())
    val imageBooks: LiveData<BooksRepoUIState> get() = _imageBooks

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
                _allBooks.value?.data?.let { books ->
                    books.filter { it.bookType == BookType.WORD_BOOK }
                } ?: run { listOf() }
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
                _allBooks.value?.data?.let { books ->
                    books.filter { it.bookType == BookType.PICTURE_BOOK }
                } ?: run { listOf() }
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

    fun sortPopular() = viewModelScope.launch {
        val allBooks = withContext(Dispatchers.Default) {
            async {
                _allBooks.value?.data?.let { books ->
                    books.sortedByDescending { it.totalOfViews }
                } ?: run { listOf() }
            }.await()
        }

        val wordBooks = withContext(Dispatchers.Default) {
            async {
                allBooks.filter { it.bookType == BookType.WORD_BOOK }
            }.await()
        }

        val imageBooks = withContext(Dispatchers.Default) {
            async {
                allBooks.filter { it.bookType == BookType.PICTURE_BOOK }
            }.await()
        }

        withContext(Dispatchers.Main) {
            if (allBooks.isNotEmpty()) {
                _allBooks.value = _allBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = allBooks
                )
                _wordBooks.value = _wordBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = wordBooks
                )
                _imageBooks.value = _imageBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = imageBooks
                )
            }
        }

    }

    fun sortNewest() = viewModelScope.launch {
        val allBooks = withContext(Dispatchers.Default) {
            async {
                _allBooks.value?.data?.let { books ->
                    books.sortedByDescending { it.publish }
                } ?: run { listOf() }
            }.await()
        }

        val wordBooks = withContext(Dispatchers.Default) {
            async {
                allBooks.filter { it.bookType == BookType.WORD_BOOK }
            }.await()
        }

        val imageBooks = withContext(Dispatchers.Default) {
            async {
                allBooks.filter { it.bookType == BookType.PICTURE_BOOK }
            }.await()
        }

        withContext(Dispatchers.Main) {
            if (allBooks.isNotEmpty()) {
                _allBooks.value = _allBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = allBooks
                )
                _wordBooks.value = _wordBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = wordBooks
                )
                _imageBooks.value = _imageBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = imageBooks
                )
            }
        }
    }

    fun sortOldest() = viewModelScope.launch {
        val allBooks = withContext(Dispatchers.Default) {
            async {
                _allBooks.value?.data?.let { books ->
                    books.sortedBy { it.publish }
                } ?: run { listOf() }
            }.await()
        }

        val wordBooks = withContext(Dispatchers.Default) {
            async {
                allBooks.filter { it.bookType == BookType.WORD_BOOK }
            }.await()
        }

        val imageBooks = withContext(Dispatchers.Default) {
            async {
                allBooks.filter { it.bookType == BookType.PICTURE_BOOK }
            }.await()
        }

        withContext(Dispatchers.Main) {
            if (allBooks.isNotEmpty()) {
                _allBooks.value = _allBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = allBooks
                )
                _wordBooks.value = _wordBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = wordBooks
                )
                _imageBooks.value = _imageBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = imageBooks
                )
            }
        }
    }

    fun updateProgressBar(book: Book) = viewModelScope.launch {
        val data = withContext(Dispatchers.Default) {
            async {
                _allBooks.value?.data?.let { updateItemInList(it, book) }
            }.await()
        }

        withContext(Dispatchers.Main) {
            if (data.isNullOrEmpty()) {
                _allBooks.value = _allBooks.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = data
                )
            }
        }
    }

    private fun updateItemInList(books: List<Book>, book: Book): List<Book> {
        books.first { it.id == book.id }.apply {
            this.duration = this.duration?.plus(Random.nextInt(10, 20))
        }
        return books
    }
}

