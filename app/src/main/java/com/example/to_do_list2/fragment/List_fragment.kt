package com.example.to_do_list2.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list2.R
import com.example.to_do_list2.roomDb.Todo
import com.example.to_do_list2.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_list_fragment.*
import kotlinx.android.synthetic.main.fragment_list_fragment.view.*

class list_fragment : Fragment(), ListAdapter.TodoEvents {

    private lateinit var mTodoViewModel: TodoViewModel

    companion object {
        fun newInstance(): list_fragment {
            return list_fragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_fragment, container, false)

        val adapter = ListAdapter(this)
        val recyclerView = view.list
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        //Observer
        mTodoViewModel.readAllTodo.observe(viewLifecycleOwner, Observer { todo ->
            adapter.updateList(todo)
        })

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

        if (!TextUtils.isEmpty(todo)) {
            val data = Todo(0, todo)
            mTodoViewModel.addTodo(data)
            Toast.makeText(requireContext(), "added successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Nothing to add", Toast.LENGTH_SHORT).show()
        }
    }
}