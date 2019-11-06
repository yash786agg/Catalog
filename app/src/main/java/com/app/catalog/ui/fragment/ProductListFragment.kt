package com.app.catalog.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.catalog.R
import com.app.catalog.domain.entities.Products
import com.app.catalog.commons.extensions.ProductItem
import com.app.catalog.ui.activity.ProductDetailsActivity
import com.app.catalog.ui.adapter.ProductListAdapter
import com.app.catalog.vm.CategoryViewModel
import com.app.catalog.commons.utils.Constants.Companion.ARG_SECTION_NUMBER
import com.app.catalog.commons.utils.Constants.Companion.EXTRA_PRODUCT
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductListFragment : Fragment(), ProductItem {

    // FOR DATA ---
    private var sectionNumber: Int = 0
    private val categoryVM: CategoryViewModel by sharedViewModel()
    private val productListAdapter = ProductListAdapter()
    private var recylvProduct: RecyclerView? = null

    /**
     * Create a new instance of ProductListFragment, providing "sectionNumber"
     * as an argument.
     */

    companion object {
        fun newInstance(sectionNumber: Int): ProductListFragment {

            /*  Initialization of Fragment Instance */
            val fragment = ProductListFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sectionNumber = if (arguments != null) arguments!!.getInt(ARG_SECTION_NUMBER) else 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.product_fragment, container, false)

        recylvProduct = view.findViewById(R.id.recylv_product)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
        configureObservables()
    }

    private fun configureObservables() {

        categoryVM.category.observe(this, Observer {
            it?.let { productListAdapter.items = it[sectionNumber].products }
        })
    }

    private fun initRecyclerView() {
        /*
         * Setup the adapter class for the RecyclerView
         * */

        recylvProduct?.layoutManager = GridLayoutManager(activity, 2)
        recylvProduct?.adapter = productListAdapter
        productListAdapter.setonProjectItemClickListener(this)
    }

    override fun onProductItemClickListener(products: Products?) {

        products?.let {
            val intent = Intent(activity, ProductDetailsActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, products)
            startActivity(intent)
        }
    }
}