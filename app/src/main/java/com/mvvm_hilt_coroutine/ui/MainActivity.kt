package com.mvvm_hilt_coroutine.ui

import android.os.Bundle
import android.view.Window
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.mvvm_hilt_coroutine.ui.base.BaseActivity
import com.mvvm_hilt_coroutine.ui.products.ProductsFragment
import com.mvvm_hilt_coroutine.utils.CommunicatorFragmentInterface
import com.photopicker.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(),CommunicatorFragmentInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        addContentFragment(ProductsFragment.newInstance(),false)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
               if (supportFragmentManager.backStackEntryCount>0){
                   supportFragmentManager.popBackStack()
               }else {
                   finish()
               }
            }
        })
    }


    override fun addContentFragment(fragment: Fragment?, addToBackStack: Boolean) {
        if (!isFinishing) {
            if (fragment == null) {
                return
            }

            val supportedFragmentManager = supportFragmentManager
            val fragmentManager = supportedFragmentManager.beginTransaction()

            if (addToBackStack)
                fragmentManager.addToBackStack(fragment.javaClass.name)

            fragmentManager.add(R.id.frame_container, fragment)
                .commitAllowingStateLoss()
        }
    }

    override fun setContentFragment(fragment: Fragment?, addToBackStack: Boolean) {

        if (!isFinishing) {

            if (fragment == null) {
                return
            }

            val supportedFragmentManager = supportFragmentManager
            val fragmentManager = supportedFragmentManager.beginTransaction()

            if (addToBackStack){
                fragmentManager.addToBackStack(fragment.javaClass.name)
            }

            fragmentManager.replace(R.id.frame_container, fragment)
                .commitAllowingStateLoss()

        }
    }

}