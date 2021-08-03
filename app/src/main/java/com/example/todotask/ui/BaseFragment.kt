package com.example.todotask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<V : ViewBinding> : Fragment() {

    abstract val bindingInflater: (LayoutInflater, ViewGroup?) -> V

    @Suppress("UNCHECKED_CAST")
    protected val binding: V
        get() = _binding as V

    private var _binding: ViewBinding? = null

    private val navController: NavController
        get() = requireView().findNavController()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater, container)
        return requireNotNull(_binding).root
    }

    protected fun navigateTo(action: NavDirections) {
        navController.navigate(action)
    }
}