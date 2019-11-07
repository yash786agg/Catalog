package com.app.catalog.ui.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.app.catalog.R
import com.app.catalog.ui.activity.CategoryActivity
import com.app.catalog.utils.ConstantTest.Companion.TEST_MILK_VALUE
import com.app.catalog.utils.EspressoTestingIdlingResource
import com.app.catalog.utils.RecyclerViewMatcher
import com.app.catalog.utils.TabsMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ProductListFragmentTest {

    private var fragmentLoadedIdlingResource: IdlingResource? = null
    @get:Rule
    var activityRule: ActivityTestRule<CategoryActivity> =
        ActivityTestRule(CategoryActivity::class.java)

    @Before
    fun init() {
        fragmentLoadedIdlingResource = EspressoTestingIdlingResource(
            activityRule.activity.supportFragmentManager,
            R.id.viewPager
        )
        //registering the idling resource so espresso waits for it
        IdlingRegistry.getInstance().register(fragmentLoadedIdlingResource)
    }

    @Test
    fun clickOnDrink() {
        onView(withId(R.id.tabLayout)).perform(TabsMatcher(1))
    }

    @Test
    fun matchDataInRecyclerView() {
        onView(
            RecyclerViewMatcher(R.id.recylv_product)
                .atPositionOnView(2, R.id.product_tv))
            .check(ViewAssertions.matches(ViewMatchers.withText(TEST_MILK_VALUE)))
            .perform(click())
    }

    @After
    fun unregisterIdlingResource() {
        if (fragmentLoadedIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(fragmentLoadedIdlingResource)
        }
    }
}