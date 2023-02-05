package com.alireza.core.extentions

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController


fun Fragment.safeNavigation(
    action: NavDirections,
    rootFragmentId: Int,
    navOptions: NavOptions? = null
) {
    findNavController().apply {
        if (currentDestination?.id == rootFragmentId) {
            navigate(directions = action, navOptions = navOptions)
        }
    }
}

fun Fragment.safeNavigation(
    @IdRes action: Int,
    rootFragmentId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null
) {
    findNavController().apply {
        if (currentDestination?.id == rootFragmentId) {
            navigate(resId = action, args = args, navOptions = navOptions)
        }
    }
}

fun Fragment.hideKeyBoard() {
    (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).run {
        view?.let { notNullView ->
            hideSoftInputFromWindow(notNullView.windowToken, 0)
        }
    }
}