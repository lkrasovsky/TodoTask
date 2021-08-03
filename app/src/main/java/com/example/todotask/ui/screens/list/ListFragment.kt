package com.example.todotask.ui.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todotask.databinding.FragmentListBinding
import com.example.todotask.ui.BaseFragment
import com.example.todotask.ui.adapter.TodosAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment<FragmentListBinding>() {

    private val viewModel: ListViewModel by viewModel()

    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentListBinding = { inflater, container ->
        FragmentListBinding.inflate(inflater, container, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        observeViewModel()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTodos()
    }

    private fun observeViewModel() {
        viewModel.todosLiveData.observe(viewLifecycleOwner) { todoItems ->
            binding.todosRecycler.adapter = TodosAdapter(todoItems) { todoId ->
                navigateTo(ListFragmentDirections.actionListFragmentToDetailsFragment(todoId))
            }
        }
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {

        }
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {

        }
    }
}