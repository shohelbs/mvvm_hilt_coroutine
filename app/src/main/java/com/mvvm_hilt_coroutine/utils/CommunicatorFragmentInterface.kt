package com.mvvm_hilt_coroutine.utils

import androidx.fragment.app.Fragment

interface CommunicatorFragmentInterface {
    fun setContentFragment(fragment: Fragment?, addToBackStack: Boolean)
    fun addContentFragment(fragment: Fragment?, addToBackStack: Boolean)
}