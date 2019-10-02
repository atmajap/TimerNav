package com.example.timernav.ui.log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.timernav.R
import com.example.timerretrofit.DataBaseHandler
import kotlinx.android.synthetic.main.fragment_log.view.*

class LogFragment : Fragment() {

    private lateinit var logViewModel: LogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logViewModel =
            ViewModelProviders.of(this).get(LogViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_log, container, false)
        val context = root.context
        var db = DataBaseHandler(context)
        root.text_log.text = ""
        var data = db.readData()
        for (i in 0..data.size-1){
            root.text_log.append(data.get(i).id.toString() + " " + data.get(i).userId.toString() + " "
            + data.get(i).time1 + " " + data.get(i).time2 + " " + data.get(i).time3 + " " + data.get(i).time4 + " "
            + data.get(i).time5 + " " + data.get(i).time6 + " " + data.get(i).timestamp + "\n")
        }

//        val textView: TextView = root.findViewById(R.id.text_slideshow)
//        logViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        return root
    }
}