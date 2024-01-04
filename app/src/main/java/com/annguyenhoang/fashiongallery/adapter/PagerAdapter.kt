package com.annguyenhoang.fashiongallery.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.annguyenhoang.fashiongallery.fragment.AllBookFragment
import com.annguyenhoang.fashiongallery.fragment.ImageBookFragment
import com.annguyenhoang.fashiongallery.fragment.WordBookFragment

class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AllBookFragment.newInstance(position)
            }

            1 -> {
                AllBookFragment.newInstance(position)
//                WordBookFragment()
            }

            else -> {
                AllBookFragment.newInstance(position)
//                ImageBookFragment()
            }
        }
    }
}