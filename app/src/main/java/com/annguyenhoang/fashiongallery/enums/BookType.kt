package com.annguyenhoang.fashiongallery.enums

enum class BookType(val id: Int, val typeName: String) {
    ALL(0, "Tất cả"),
    WORD_BOOK(1, "Sách chữ"),
    PICTURE_BOOK(2, "Sách hình"),
    AUDIO_BOOK(3, "Sách nói"),
}