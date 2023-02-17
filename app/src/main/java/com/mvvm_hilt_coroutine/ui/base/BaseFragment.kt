package com.mvvm_hilt_coroutine.ui.base
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.mvvm_hilt_coroutine.utils.CommunicatorFragmentInterface

abstract class BaseFragment : Fragment(){

    var myCommunicator: CommunicatorFragmentInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            myCommunicator = context as CommunicatorFragmentInterface?
        } catch (_: ClassCastException) {
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        //No call for super(). Bug on API Level > 11.
    }

    fun closeKeyboard() {
        try {
            val view = requireActivity().currentFocus
            if (view != null) {
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun routeActivity(context:Context,cls: Class<*>) {
        val intent = Intent(context, cls)
        startActivity(intent)
    }

    fun routeActivity(intent: Intent) {
        startActivity(intent)
    }

    fun routeActivity(activity:Activity,cls: Class<*>, finish: Boolean) {
        routeActivity(activity,cls)
        if (finish && !activity.isFinishing) {
            activity.finish()
        }
    }

    fun routeActivity(activity:Activity,intent: Intent, finish: Boolean) {
        routeActivity(intent)
        if (finish && !activity.isFinishing) {
            activity.finish()
        }
    }

    fun hasPermission(perm: String): Boolean {
        return (ActivityCompat.checkSelfPermission(requireContext(), perm) == PackageManager.PERMISSION_GRANTED)
    }

    fun hasPermission(permList: Array<String?>): Boolean {
        var res = true
        for (perm in permList) {
            if (!hasPermission(perm!!)) {
                res = false
            }
        }
        if (Build.VERSION.SDK_INT < 22) {
            res = true
        }
        return res
    }
}