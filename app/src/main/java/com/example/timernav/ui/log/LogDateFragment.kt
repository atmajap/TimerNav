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
import com.example.timernav.databinding.FragmentLogDateBinding

class LogDateFragment : Fragment(), DateAdapter.OnDateClickListener {

    override fun onDateClick(item: String) {
        // TODO go to next page
        Log.d("lalala", "date id $item")
    }

    private lateinit var viewModel: LogDateViewModel
    private lateinit var adapter : DateAdapter
    private lateinit var binding : FragmentLogDateBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLogDateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LogDateViewModel::class.java)
        viewModel.getDateData()

        adapter = DateAdapter(context!!, arrayListOf(), this)
        binding.rvDate.layoutManager = LinearLayoutManager(context)
        binding.rvDate.adapter = adapter

        viewModel.dateList.observe(this, Observer { data ->
            data?.let {
                adapter.updateData(data)
            }
        })

    }

}
