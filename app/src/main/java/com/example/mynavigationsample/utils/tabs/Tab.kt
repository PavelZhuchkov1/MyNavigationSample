package com.example.mynavigationsample.utils.tabs

import com.example.mynavigationsample.R

enum class Tab(val itemId: Int, val tag: String) {
    MY_BOOKS(R.id.item_my_books, "MY_BOOKS"),
    SHOWCASE(R.id.item_showcase, "SHOWCASE"),
    PROFILE(R.id.item_profile, "PROFILE")
}