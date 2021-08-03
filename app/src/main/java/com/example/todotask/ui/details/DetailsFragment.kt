package com.example.todotask.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.todotask.databinding.FragmentDetailsBinding
import com.example.todotask.ui.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentDetailsBinding = { inflater, container ->
        FragmentDetailsBinding.inflate(inflater, container, false)
    }
}