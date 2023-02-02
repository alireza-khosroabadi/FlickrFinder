package com.alireza.core.presentation.fragment

import androidx.viewbinding.ViewBinding


/**
 * BaseFragment is base class for Fragment's to reduce Boilerplate codes to create binding object
 * and has additional abstract function to observe ViewModel States
 * */
abstract class BaseObserverFragment<T : ViewBinding> :
    BaseFragment<T>() {

    override fun onStart() {
        super.onStart()
        observe()
    }

    protected abstract fun observe()

}