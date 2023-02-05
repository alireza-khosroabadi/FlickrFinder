package com.alireza.core.presentation.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * BaseBottomSheet is base class for BottomSheet's to reduce Boilerplate codes to create binding object
 * */
abstract class BaseBottomSheet<T: ViewBinding>():BottomSheetDialogFragment(){

    private var _binding: T? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        donOnCreateView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    abstract fun getViewBinding(): T
    protected abstract fun donOnCreateView()
    protected abstract fun setupViews()
}