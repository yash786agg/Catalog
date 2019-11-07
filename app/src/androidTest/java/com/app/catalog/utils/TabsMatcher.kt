package com.app.catalog.utils

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Matcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

class TabsMatcher(private val position: Int) : ViewAction {

    override fun getConstraints(): Matcher<View> {
        return isDisplayed()
    }

    override fun getDescription(): String {
        return "Click on tab"
    }

    override fun perform(uiController: UiController, view: View) {
        if (view is TabLayout) {
            val tab = view.getTabAt(position)
            tab?.select()
        }
    }
}