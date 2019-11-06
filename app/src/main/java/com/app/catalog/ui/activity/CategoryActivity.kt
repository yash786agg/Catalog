package com.app.catalog.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.catalog.R
import com.app.catalog.datasource.api.NetworkState
import com.app.catalog.domain.entities.CategoryApiResponse
import com.app.catalog.ui.fragment.ProductListFragment
import com.app.catalog.vm.CategoryViewModel
import com.app.catalog.commons.utils.UiHelper
import com.app.catalog.commons.viewpager.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.product_activity.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryActivity : AppCompatActivity() {

    // FOR DATA ---
    private val categoryVM: CategoryViewModel by viewModel()
    private val uiHelper: UiHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_activity)

        /*
         * Check Internet Connection
         * */

        if (uiHelper.getConnectivityStatus()) configureObservables()
        else uiHelper.showSnackBar(product_rootView,
            resources.getString(R.string.error_message_network)
        )
    }

    private fun setupViewPager(list: List<CategoryApiResponse>) {

        val adapter = ViewPagerAdapter(supportFragmentManager)

        for (i in list.indices) {

            val fragment = ProductListFragment()

            //Setting up the name of Tabs in a TabLayout
            tabLayout.addTab(tabLayout.newTab().setText(list[i].name))

            list[i].name?.let { adapter.addFragment(fragment, it) }
        }

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                //setCurrentItem is used to visible Item of View Pager of Selected Tab Position.
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun configureObservables() {

        categoryVM.category.observe(this, Observer {

            it?.let {
                /* Add Fragment based on Product Type
                 * @return Item Type to be displayed*/
                setupViewPager(it)
                tabLayout.setupWithViewPager(viewPager)
            }
        })

        /*
         * Progress Updater
         * */
        categoryVM.networkState.observe(this, Observer {
            if (it != null) {
                when (it) {
                    is NetworkState.Loading -> showProgressBar(true)
                    is NetworkState.Success -> showProgressBar(false)
                    is NetworkState.Error -> {
                        uiHelper.showSnackBar(product_rootView, it.code.toString())
                        showProgressBar(false)
                    }
                }
            }
        })
    }

    // UPDATE UI ----
    private fun showProgressBar(display: Boolean) {
        if (!display) progress_bar.visibility = View.GONE
        else progress_bar.visibility = View.VISIBLE
    }
}
