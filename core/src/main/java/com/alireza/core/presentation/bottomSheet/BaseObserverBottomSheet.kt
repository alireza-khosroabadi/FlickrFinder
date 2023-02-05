package com.alireza.core.presentation.bottomSheet

import androidx.viewbinding.ViewBinding


/**
 * BaseBottomSheet is base class for BottomSheet's to reduce Boilerplate codes to create binding object
 * and has additional abstract function to observe ViewModel States
 * */
abstract class BaseObserverBottomSheet<T : ViewBinding> :
    BaseBottomSheet<T>() {

    override fun onStart() {
        super.onStart()
        observe()
    }

    protected abstract fun observe()

}