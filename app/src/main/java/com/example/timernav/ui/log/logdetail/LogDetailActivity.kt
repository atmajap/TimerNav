package com.example.timernav.ui.log.logdetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.timernav.R
import com.example.timernav.databinding.ActivityLogDetailBinding
import com.example.timernav.ui.log.chart.LogChartActivity
import com.example.timernav.utils.Extensions
import kotlinx.android.synthetic.main.activity_log_detail.*
import kotlinx.android.synthetic.main.activity_log_user_detail.*
import kotlinx.android.synthetic.main.activity_log_user_detail.log_user_toolbar

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

        btn_chart.setOnClickListener { onChartBtnClick() }
    }

    fun initData() {
        binding.tvUseridtext.text = intent.getIntExtra("userId", -1).toString()
        binding.tvDatetext.text = intent.getStringExtra("date")
        binding.tvLap1text.text = Extensions.convertToTime(intent.getStringExtra("time1")?: "")
        binding.tvLap2text.text = Extensions.convertToTime(intent.getStringExtra("time2")?: "")
        binding.tvLap3text.text = Extensions.convertToTime(intent.getStringExtra("time3")?: "")
        binding.tvLap4text.text = Extensions.convertToTime(intent.getStringExtra("time4")?: "")
        binding.tvLap5text.text = Extensions.convertToTime(intent.getStringExtra("time5")?: "")
        binding.tvLap6text.text = Extensions.convertToTime(intent.getStringExtra("time6")?: "")
    }

    fun onChartBtnClick() {
        val sendIntent = Intent(this, LogChartActivity::class.java)
        sendIntent.putExtra("time1", intent.getStringExtra("time1"))
        sendIntent.putExtra("time2", intent.getStringExtra("time2"))
        sendIntent.putExtra("time3", intent.getStringExtra("time3"))
        sendIntent.putExtra("time4", intent.getStringExtra("time4"))
        sendIntent.putExtra("time5", intent.getStringExtra("time5"))
        sendIntent.putExtra("time6", intent.getStringExtra("time6"))
        startActivity(sendIntent)
    }
}
