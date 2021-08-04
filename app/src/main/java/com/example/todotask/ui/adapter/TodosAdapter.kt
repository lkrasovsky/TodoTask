package com.example.todotask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todotask.R
import com.example.todotask.databinding.ItemTodoBinding

class TodosAdapter(
    private val todos: List<TodoItem>,
    private val onClick: (todoId: Int) -> Unit
) : RecyclerView.Adapter<TodosAdapter.TodoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        val binding = ItemTodoBinding.bind(view)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int = todos.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.bind(todo)
    }

    override fun onViewDetachedFromWindow(holder: TodoViewHolder) {
        holder.itemView.clearAnimation()
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: TodoItem) {
            binding.titleText.text = todo.title
            itemView.setOnClickListener { onClick(todo.id) }
        }
    }

    class TodoItem(
        val id: Int,
        val title: String
    )
}