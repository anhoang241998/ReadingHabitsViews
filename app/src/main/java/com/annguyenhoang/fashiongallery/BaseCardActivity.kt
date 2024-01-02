package com.annguyenhoang.fashiongallery

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_VERTICAL
import com.annguyenhoang.fashiongallery.cards.Card
import com.google.android.material.tabs.TabLayout
import kotlin.math.abs

/**
 * Base class for the two activities in the demo. Sets up the list of cards and implements UI to
 * jump to arbitrary cards using setCurrentItem, either with or without smooth scrolling.
 */
abstract class BaseCardActivity : FragmentActivity() {

    lateinit var viewPager: ViewPager2
    private lateinit var cardSelector: Spinner
    private lateinit var smoothScrollCheckBox: CheckBox
    private lateinit var rotateCheckBox: CheckBox
    private lateinit var translateCheckBox: CheckBox
    private lateinit var scaleCheckBox: CheckBox
    private lateinit var gotoPage: Button

    private val translateX
        get() = viewPager.orientation == ORIENTATION_VERTICAL && translateCheckBox.isChecked

    private val translateY
        get() = viewPager.orientation == ORIENTATION_HORIZONTAL && translateCheckBox.isChecked

    open val layoutId: Int = R.layout.activity_no_tablayout

    private val animator = ViewPager2.PageTransformer { page, position ->
        val absPos = abs(position)
        page.apply {
            rotation = if (rotateCheckBox.isChecked) position * 360 else 0f
            translationY = if (translateY) absPos * 500f else 0f
            translationX = if (translateX) absPos * 350f else 0f
            if (scaleCheckBox.isChecked) {
                val scale = if (absPos > 1) 0f else 1 - absPos
                scaleX = scale
                scaleY = scale
            } else {
                scaleX = 1f
                scaleY = 1f
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        viewPager = findViewById(R.id.view_pager)
        cardSelector = findViewById(R.id.card_spinner)
        smoothScrollCheckBox = findViewById(R.id.smooth_scroll_checkbox)
        rotateCheckBox = findViewById(R.id.rotate_checkbox)
        translateCheckBox = findViewById(R.id.translate_checkbox)
        scaleCheckBox = findViewById(R.id.scale_checkbox)
        gotoPage = findViewById(R.id.jump_button)

        UserInputController(viewPager, findViewById(R.id.disable_user_input_checkbox)).setUp()
        OrientationController(viewPager, findViewById(R.id.orientation_spinner)).setUp()
        cardSelector.adapter = createCardAdapter()

        viewPager.setPageTransformer(animator)
        gotoPage.setOnClickListener {
            val card = cardSelector.selectedItemPosition
            val smoothScroll = smoothScrollCheckBox.isChecked
            viewPager.setCurrentItem(card, smoothScroll)
        }

        rotateCheckBox.setOnClickListener { viewPager.requestTransform() }
        translateCheckBox.setOnClickListener { viewPager.requestTransform() }
        scaleCheckBox.setOnClickListener { viewPager.requestTransform() }
    }

    private fun createCardAdapter(): SpinnerAdapter {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Card.DECK)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        return adapter
    }
}