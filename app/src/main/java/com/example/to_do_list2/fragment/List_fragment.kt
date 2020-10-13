package com.example.to_do_list2.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_list2.R
import com.example.to_do_list2.roomDb.Todo
import com.example.to_do_list2.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.list_fragment.view.*
import java.util.*

class ListFragment : Fragment(), ListAdapter.TodoEvents {

    private lateinit var mTodoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        // Get the adapter
        val adapter = ListAdapter(this)
        val recyclerView = view.list
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        //Observer
        mTodoViewModel.readAllTodo.observe(viewLifecycleOwner, Observer { todo ->
            adapter.updateList(todo)
        })

        val itemTouchHelperCallback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.del(viewHolder.adapterPosition)
            }

        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        view.add_btn.setOnClickListener {
            insertTaskToDb()
        }

        return view
    }

    override fun onDeleteClicked(todo: Todo) {
        mTodoViewModel.deleteTodo(todo)
    }

    private fun insertTaskToDb() {
        val todo = todo_edit.text.toString()
        todo_edit.text.clear()

        if (!TextUtils.isEmpty(todo)) {
            val data = Todo(0, todo)
            mTodoViewModel.addTodo(data)
            Toast.makeText(requireContext(), "added successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Nothing to add", Toast.LENGTH_SHORT).show()
        }
    }
}