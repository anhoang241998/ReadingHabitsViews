package com.annguyenhoang.fashiongallery.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.annguyenhoang.fashiongallery.fragment.BookFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                BookFragment(typeBook = position)
            }

            1 -> {
                BookFragment(typeBook = position)
            }

            else -> {
                BookFragment(typeBook = position)
            }
        }
    }
}