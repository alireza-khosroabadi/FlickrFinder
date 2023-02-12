package com.alireza.core.presentation.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

open class BaseViewModel : ViewModel() {
//    protected val _errorState = MutableSharedFlow<BaseViewModelState>(
//        replay = 1,
//        onBufferOverflow = BufferOverflow.DROP_OLDEST
//    )
//    val errorState: Flow<BaseViewModelState?> = _errorState.distinctUntilChanged()

    protected val _errorState = MutableStateFlow<BaseViewModelState>(Initialize)
    val errorState: StateFlow<BaseViewModelState> = _errorState


    protected fun resetState(){
        _errorState.value = Initialize
    }
}