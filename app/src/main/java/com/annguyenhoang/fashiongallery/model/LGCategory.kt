package com.annguyenhoang.fashiongallery.model

import android.graphics.Color
import com.annguyenhoang.fashiongallery.R

data class LGCategory(
    val id: Int? = 0,
    val name: String? = "",
    val backgroundColor: Int = R.color.yellow_700,
    val totalOfBooks: Int? = 0,
    val image: String? = "",
) {
    companion object {
        fun mock() = listOf(
            LGCategory(id = 1, name = "Tiểu thuyết", backgroundColor = R.color.blue_700, totalOfBooks = 12),
            LGCategory(id = 2, name = "Khoa học - viễn tưởng", backgroundColor = R.color.blue_300, totalOfBooks = 100),
            LGCategory(id = 3, name = "Truyện cổ tích", backgroundColor = R.color.green_100, totalOfBooks = 1),
            LGCategory(id = 4, name = "Sách kinh điển", backgroundColor = R.color.green_700, totalOfBooks = 999),
            LGCategory(id = 5, name = "Tản văn", backgroundColor = R.color.red_100, totalOfBooks = 500),
            LGCategory(id = 6, name = "Kinh dị", backgroundColor = R.color.teal_200, totalOfBooks = 120),
            LGCategory(id = 7, name = "Hài hước", backgroundColor = R.color.green_300, totalOfBooks = 12),
        )
    }
}
