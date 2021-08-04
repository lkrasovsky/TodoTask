package com.example.todotask.ui.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.todotask.databinding.FragmentDetailsBinding
import com.example.todotask.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentDetailsBinding = { inflater, container ->
        FragmentDetailsBinding.inflate(inflater, container, false)
    }

    private val viewModel: DetailsViewModel by viewModel()
    private val navArgs: DetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        observeViewModel()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTodo(navArgs.todoId)
    }

    private fun observeViewModel() {
        viewModel.todoLiveData.observe(viewLifecycleOwner) {
            binding.titleText.text = it.title
            binding.dateText.text = it.dueOn
        }
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            val visibility = if (it) View.VISIBLE else View.GONE
            binding.progressBarView.visibility = visibility
        }
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            it?.let {
                binding.errorText.visibility = View.VISIBLE
                binding.errorText.text = it
            } ?: run {
                binding.errorText.visibility = View.GONE
            }
        }
    }
}