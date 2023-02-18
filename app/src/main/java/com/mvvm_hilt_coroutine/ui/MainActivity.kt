package com.mvvm_hilt_coroutine.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mvvm_hilt_coroutine.ui.base.BaseActivity
import com.mvvm_hilt_coroutine.ui.photos.ProductsFragment
import com.mvvm_hilt_coroutine.utils.CommunicatorFragmentInterface
import com.photopicker.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(),CommunicatorFragmentInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentFragment(ProductsFragment.newInstance(),false)
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
}