package com.example.timernav.ui.log.chart

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.timernav.R
import com.example.timernav.databinding.ActivityLogChartBinding
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.Utils

class LogChartActivity : AppCompatActivity(), OnChartValueSelectedListener {

    private lateinit var binding : ActivityLogChartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_chart)
        binding.lifecycleOwner = this
        title = "Chart"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val time1 = (intent.getStringExtra("time1")?: "").toFloat()
        val time2 = (intent.getStringExtra("time2")?: "").toFloat()
        val time3 = (intent.getStringExtra("time3")?: "").toFloat()
        val time4 = (intent.getStringExtra("time4")?: "").toFloat()
        val time5 = (intent.getStringExtra("time5")?: "").toFloat()
        val time6 = (intent.getStringExtra("time6")?: "").toFloat()

        val optionList: ArrayList<String> = ArrayList()
        optionList.add("By Time")
        optionList.add("By Velocity")

        binding.chartSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, optionList)

        binding.chartSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    binding.chart1.visibility = View.VISIBLE
                    binding.chart2.visibility = View.GONE
                    createChart(binding.chart1, time1, time2, time3, time4, time5, time6)
                } else {
                    binding.chart1.visibility = View.GONE
                    binding.chart2.visibility = View.VISIBLE
                    createChart(binding.chart2, (10/time1), (20/time2), (30/time3), (40/time4), (50/time5), (60/time6))
                }
            }
        }
    }

    fun createChart(chart: LineChart, y1: Float, y2: Float, y3: Float, y4: Float, y5: Float, y6: Float) {
        chart.setBackgroundColor(Color.WHITE)
        // disable description text
        chart.description.isEnabled = false

        // enable touch gestures
        chart.setTouchEnabled(true)

        // set listeners
        chart.setOnChartValueSelectedListener(this)
        chart.setDrawGridBackground(false)
        // enable scaling and dragging
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        // chart.setScaleXEnabled(true);
        // chart.setScaleYEnabled(true);

        // force pinch zoom along both axis
        chart.setPinchZoom(true)
        val yValues = ArrayList<Entry>()
        yValues.add(Entry(10f, y1))
        yValues.add(Entry(20f, y2))
        yValues.add(Entry(30f, y3))
        yValues.add(Entry(40f, y4))
        yValues.add(Entry(50f, y5))
        yValues.add(Entry(60f, y6))
        val set1 = LineDataSet(yValues, "Data Set 1")


        set1.setDrawIcons(false)

        // draw dashed line
        set1.enableDashedLine(10f, 5f, 0f)

        // black lines and points
        set1.color = Color.BLACK
        set1.setCircleColor(Color.BLACK)

        // line thickness and point size
        set1.lineWidth = 1f
        set1.circleRadius = 3f

        // draw points as solid circles
        set1.setDrawCircleHole(false)

        // customize legend entry
        set1.formLineWidth = 1f
        set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        set1.formSize = 15f

        // text size of values
        set1.valueTextSize = 9f

        // draw selection line as dashed
        set1.enableDashedHighlightLine(10f, 5f, 0f)

        // set the filled area
        set1.setDrawFilled(true)
        set1.fillFormatter = IFillFormatter { dataSet, dataProvider -> chart.axisLeft.axisMinimum }

        // set color of filled area
        if (Utils.getSDKInt() >= 18) {
            // drawables only supported on api level 18 and above
            val drawable = ContextCompat.getDrawable(this, R.drawable.fade_red)
            set1.fillDrawable = drawable
        } else {
            set1.fillColor = Color.BLACK
        }

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)

        val data = LineData(dataSets)
        chart.data = data
    }

    override fun onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.")
    }

    override fun onValueSelected(e: Entry, h: Highlight) {
        Log.i("Entry selected", e.toString())
//        Log.i("LOW HIGH", "low: " + binding.chart1.lowestVisibleX + ", high: " + binding.chart1.highestVisibleX)
//        Log.i("MIN MAX", "xMin: " + binding.chart1.xChartMin + ", xMax: " + binding.chart1.xChartMax + ", yMin: " + binding.chart1.yChartMin + ", yMax: " + binding.chart1.yChartMax)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
