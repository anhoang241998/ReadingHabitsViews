package com.annguyenhoang.fashiongallery.model

import com.annguyenhoang.fashiongallery.enums.FetchingStatus

data class BooksRepoUIState(
    val fetchingStatus: FetchingStatus? = FetchingStatus.LOADING,
    val data: List<Book>? = null,
)