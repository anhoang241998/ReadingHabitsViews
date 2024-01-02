package com.annguyenhoang.fashiongallery

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.annguyenhoang.fashiongallery.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PagerActivity : FragmentActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager)
        viewPager2.isUserInputEnabled = false

        val viewPageAdapter = ViewPagerAdapter(this)
        viewPager2.adapter = viewPageAdapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "Tất cả"
                1 -> tab.text = "Sách chữ"
                2 -> tab.text = "Sách hình"
            }
        }.attach()
    }
}