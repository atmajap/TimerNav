package com.example.timernav.ui.log.user

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.timernav.R
import com.example.timernav.databinding.ActivityLogUserBinding
import kotlinx.android.synthetic.main.activity_log_user.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timernav.model.LogDetail
import com.example.timernav.ui.log.logdetail.LogDetailActivity


class LogUserDetailActivity : AppCompatActivity(), UserDetailAdapter.OnUserClickListener {

    private lateinit var viewModel: LogUserDetailViewModel
    private lateinit var adapter : UserDetailAdapter
    private lateinit var binding : ActivityLogUserBinding

    override fun onUserClick(item: LogDetail) {
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

        viewModel = ViewModelProviders.of(this).get(LogUserDetailViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_user)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        title = "Log"
        setSupportActionBar(log_user_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        log_user_toolbar.setNavigationIcon(R.drawable.ic_back)
        log_user_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val userId = intent.getStringExtra("userId")
        viewModel.getUserData(userId)

        adapter = UserDetailAdapter(this, arrayListOf(), this)
        binding.rvUserlist.layoutManager = LinearLayoutManager(this)
        binding.rvUserlist.adapter = adapter

        viewModel.logList.observe(this, Observer { data ->
            data?.let {
                adapter.updateData(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}