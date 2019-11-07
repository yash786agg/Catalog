package com.app.catalog.utils

import androidx.fragment.app.FragmentManager
import androidx.test.espresso.IdlingResource

class EspressoTestingIdlingResource(
    private val mFragmentManager: FragmentManager,
    private val mId: Int
) : IdlingResource {
    //resource callback you use when your activity transitions to idle
    @Volatile
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return EspressoTestingIdlingResource::class.java.name
    }

    override fun isIdleNow(): Boolean {
        //simple check, if your fragment is added, then your app has became idle
        val idle = mFragmentManager.findFragmentById(mId) != null
        if (idle) {
            //IMPORTANT: make sure you call onTransitionToIdle
            resourceCallback!!.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }
}