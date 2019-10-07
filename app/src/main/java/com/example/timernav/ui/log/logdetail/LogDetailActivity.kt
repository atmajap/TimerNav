package com.example.timernav.ui.log.logdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.timernav.R
import com.example.timernav.databinding.ActivityLogDetailBinding
import kotlinx.android.synthetic.main.activity_log_user_detail.*

class LogDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLogDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_detail)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_detail)
        binding.lifecycleOwner = this
        title = "Log Detail"
        setSupportActionBar(log_user_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        log_user_toolbar.setNavigationIcon(R.drawable.ic_back)
        log_user_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        initData()
    }

    fun initData() {
        binding.tvUseridtext.text = intent.getIntExtra("userId", -1).toString()
        binding.tvDatetext.text = intent.getStringExtra("date")
        binding.tvLap1text.text = intent.getStringExtra("time1")
        binding.tvLap2text.text = intent.getStringExtra("time2")
        binding.tvLap3text.text = intent.getStringExtra("time3")
        binding.tvLap4text.text = intent.getStringExtra("time4")
        binding.tvLap5text.text = intent.getStringExtra("time5")
        binding.tvLap6text.text = intent.getStringExtra("time6")
    }
}
