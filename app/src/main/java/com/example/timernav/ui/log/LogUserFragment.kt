package com.example.timernav.ui.log

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.timernav.databinding.FragmentLogUserBinding

class LogUserFragment : Fragment(), UserAdapter.OnUserClickListener {

    override fun onUserClick(item: String) {
        //TODO go to next page
        Log.d("lalala", "user id $item")
    }

    private lateinit var viewModel: LogUserViewModel
    private lateinit var adapter : UserAdapter
    private lateinit var binding : FragmentLogUserBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLogUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LogUserViewModel::class.java)
        viewModel.getUserData()

        adapter = UserAdapter(context!!, arrayListOf(), this)
        binding.rvUser.layoutManager = LinearLayoutManager(context)
        binding.rvUser.adapter = adapter

        viewModel.userList.observe(this, Observer { data ->
                data?.let {
                    adapter.updateData(data)
                }
            })

    }

}
