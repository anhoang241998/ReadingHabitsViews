package com.annguyenhoang.fashiongallery.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class TabFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    abstract fun initViews(view: View)
    abstract fun viewsControl()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        viewsControl()
    }

}