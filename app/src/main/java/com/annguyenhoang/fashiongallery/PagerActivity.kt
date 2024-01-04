package com.annguyenhoang.fashiongallery

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.updatePadding
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.annguyenhoang.fashiongallery.adapter.PagerAdapter
import com.annguyenhoang.fashiongallery.enums.FetchingStatus
import com.annguyenhoang.fashiongallery.model.Book
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PagerActivity : FragmentActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var btnSort: Button
    private val viewModel: ListBookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager)
        btnSort = findViewById(R.id.btnSort)

        tabLayout.setOnApplyWindowInsetsListener { view, insets ->
            view.updatePadding(
                top = when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                        view.rootWindowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars()).top
                    }

                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1 -> {
                        view.rootWindowInsets?.stableInsetTop ?: 0
                    }

                    else -> 0
                }
            )
            insets
        }

        viewPager2.isUserInputEnabled = false

        val viewPageAdapter = PagerAdapter(this)
        viewPager2.adapter = viewPageAdapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "Tất cả"
                1 -> tab.text = "Sách chữ"
                2 -> tab.text = "Sách hình"
            }
        }.attach()

        btnSort.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
            val btnClose = view.findViewById<Button>(R.id.btnClose)
            btnClose.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }

        fun onRadioButtonClicked(view: View) {
            if (view is RadioButton) {
                val checked = view.isChecked

                when (view.getId()) {
                    R.id.rdoPopular ->
                        if (checked) {

//                            viewModel.wordBooks.observe(this) { uiState ->
//                                when (uiState.fetchingStatus) {
//                                    FetchingStatus.SUCCESS -> {
////                                        uiState?.data?.let { dataBooks ->
////                                bookAdapter.setBooksToList(dataBooks)
//                                        }
//                                    }
//                                    else -> {}
//                                }
//                            }
//
//                            viewModel.fetchWordBooks()
                        }

                    R.id.rdoNewest ->
                        if (checked) {
                            Log.d("TAG", "onRadioButtonClicked: ")
                            viewModel.fetchAllBooksNew()
                        }

                    R.id.rdoOldest ->
                        if (checked) {
                        }
                }
            }
        }
    }
}