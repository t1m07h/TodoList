package com.example.to_do_list2.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_list2.R
import com.example.to_do_list2.roomDb.Todo
import kotlinx.android.synthetic.main.todo_view.view.*

class ListAdapter(todoEvents: TodoEvents): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var task_list = emptyList<Todo>()
    private val listener: TodoEvents = todoEvents

    class MyViewHolder(item_view: View): RecyclerView.ViewHolder(item_view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_view, parent, false ))
    }

    override fun getItemCount(): Int {
        return task_list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current_item = task_list[position]

        holder.itemView.todo_text.text = current_item.task
        holder.itemView.del_item_btn.setOnClickListener {
            listener.onDeleteClicked(current_item)
        }
    }

    fun updateList(todos: List<Todo>) {
        this.task_list = todos
        notifyDataSetChanged()
    }

    interface TodoEvents {
        fun onDeleteClicked(todo: Todo)
    }
}