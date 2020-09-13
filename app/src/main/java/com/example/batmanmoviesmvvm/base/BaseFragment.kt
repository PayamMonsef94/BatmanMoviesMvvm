package com.example.batmanmoviesmvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    private var snackbar: Snackbar? = null

    @LayoutRes
    abstract fun getLayoutResId(): Int

    fun showSnackBar(message: String) {
        snackbar = Snackbar.make(this.requireView(), message, Snackbar.LENGTH_LONG)
        snackbar?.show()
    }

    fun hideSnackBar() {
        snackbar?.dismiss()
    }

    protected lateinit var binding: T
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<T>(inflater, getLayoutResId(), container, false).apply {
            binding = this
            binding.lifecycleOwner=this@BaseFragment
        }.root
    }
}