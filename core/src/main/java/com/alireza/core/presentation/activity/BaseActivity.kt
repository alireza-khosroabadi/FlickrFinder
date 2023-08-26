package com.alireza.core.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T: ViewBinding>:AppCompatActivity(){

    private var _binding: T? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)
        doOnCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }

    abstract fun getViewBinding(): T
    protected abstract fun doOnCreate()
}