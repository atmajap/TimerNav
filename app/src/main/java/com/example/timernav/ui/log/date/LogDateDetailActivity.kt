package com.example.timernav.ui.log.date

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timernav.R
import com.example.timernav.databinding.ActivityLogDateDetailBinding
import com.example.timernav.model.LogDetail
import com.example.timernav.ui.log.logdetail.LogDetailActivity
import kotlinx.android.synthetic.main.activity_log_date_detail.*

class LogDateDetailActivity : AppCompatActivity(), DateDetailAdapter.OnDateClickListener {

    private lateinit var viewModel: LogDateDetailViewModel
    private lateinit var adapter : DateDetailAdapter
    private lateinit var binding : ActivityLogDateDetailBinding

    override fun onDateClick(item: LogDetail) {
        val intent = Intent(this, LogDetailActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("userId", item.userId)
        intent.putExtra("date", item.date)
        intent.putExtra("time1", item.time1)
        intent.putExtra("time2", item.time2)
        intent.putExtra("time3", item.time3)
        intent.putExtra("time4", item.time4)
        intent.putExtra("time5", item.time5)
        intent.putExtra("time6", item.time6)
        intent.putExtra("sequence", item.sequence)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LogDateDetailViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_date_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        title = "Log"
        setSupportActionBar(log_date_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        log_date_toolbar.setNavigationIcon(R.drawable.ic_back)
        log_date_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val date = intent.getStringExtra("date")
        viewModel.getDateData(date)

        adapter = DateDetailAdapter(this, arrayListOf(), this)
        binding.rvDatelist.layoutManager = LinearLayoutManager(this)
        binding.rvDatelist.adapter = adapter

        viewModel.logList.observe(this, Observer { data ->
            data?.let {
                adapter.updateData(data)
            }
        })
    }
}
