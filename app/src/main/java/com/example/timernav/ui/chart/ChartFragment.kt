package com.example.timernav.ui.chart

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.timernav.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.Utils
import kotlinx.android.synthetic.main.fragment_chart.*
import kotlinx.android.synthetic.main.fragment_chart.view.*

class ChartFragment : Fragment(), OnChartValueSelectedListener {

    private var chart: LineChart? = null
    private lateinit var chartViewModel: ChartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chartViewModel =
            ViewModelProviders.of(this).get(ChartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chart, container, false)
        chart = root.line_chart
        chart!!.setBackgroundColor(Color.WHITE)
        // disable description text
        chart!!.description.isEnabled = false

        // enable touch gestures
        chart!!.setTouchEnabled(true)

        // set listeners
        chart!!.setOnChartValueSelectedListener(this)
        chart!!.setDrawGridBackground(false)
        // enable scaling and dragging
        chart!!.isDragEnabled = true
        chart!!.setScaleEnabled(true)
        // chart.setScaleXEnabled(true);
        // chart.setScaleYEnabled(true);

        // force pinch zoom along both axis
        chart!!.setPinchZoom(true)
        val yValues = ArrayList<Entry>()
        yValues.add(Entry(0f, 60f))
        yValues.add(Entry(1f, 70f))
        yValues.add(Entry(2f, 80f))
        yValues.add(Entry(3f, 30f))
        yValues.add(Entry(4f, 50f))
        yValues.add(Entry(5f, 60f))
        yValues.add(Entry(6f, 80f))
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
        set1.fillFormatter = IFillFormatter { dataSet, dataProvider -> chart!!.axisLeft.axisMinimum }

        // set color of filled area
        if (Utils.getSDKInt() >= 18) {
            // drawables only supported on api level 18 and above
            val drawable = ContextCompat.getDrawable(context!!, R.drawable.fade_red)
            set1.fillDrawable = drawable
        } else {
            set1.fillColor = Color.BLACK
        }


        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)

        val data = LineData(dataSets)
        chart!!.data = data



        return root
    }

    override fun onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.")
    }

    override fun onValueSelected(e: Entry, h: Highlight) {
        Log.i("Entry selected", e.toString())
        Log.i("LOW HIGH", "low: " + chart!!.lowestVisibleX + ", high: " + chart!!.highestVisibleX)
        Log.i("MIN MAX", "xMin: " + chart!!.xChartMin + ", xMax: " + chart!!.xChartMax + ", yMin: " + chart!!.yChartMin + ", yMax: " + chart!!.yChartMax)    }

}