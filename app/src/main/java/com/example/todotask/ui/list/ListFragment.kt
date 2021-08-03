package com.example.todotask.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.todotask.databinding.FragmentListBinding
import com.example.todotask.ui.BaseFragment

class ListFragment : BaseFragment<FragmentListBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentListBinding = { inflater, container ->
        FragmentListBinding.inflate(inflater, container, false)
    }


}