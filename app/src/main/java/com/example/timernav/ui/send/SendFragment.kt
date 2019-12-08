package com.example.timernav.ui.send

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.timernav.BuildConfig
import com.example.timernav.databinding.FragmentSendBinding
import com.example.timernav.model.LogDetail
import com.opencsv.CSVWriter
import kotlinx.android.synthetic.main.fragment_send.*
import org.apache.commons.lang3.ObjectUtils
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class SendFragment : Fragment() {

    val MONTHS = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    private lateinit var sendViewModel: SendViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sendViewModel =
            ViewModelProviders.of(this).get(SendViewModel::class.java)

        val binding = FragmentSendBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val permissions = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (Build.VERSION.SDK_INT >= 23) {
            if (context!!.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
            ) {
                    ActivityCompat.requestPermissions(activity as Activity, permissions, 2)
            } else {
                // Permission has already been granted
            }
        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month =   c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.etDateFrom.setOnClickListener {
            val dpd =
                context?.let { it1 ->
                    DatePickerDialog(it1, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        // Display Selected date in TextView
                        val monthName = MONTHS[month]
                        binding.etDateFrom.setText("$dayOfMonth-$monthName-$year")
                    }, year, month, day)
                }
            dpd?.show()
        }

        binding.etDateTo.setOnClickListener {
            val dpd2 =
                context?.let { it1 ->
                    DatePickerDialog(it1, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        // Display Selected date in TextView
                        val monthName = MONTHS[month]
                        binding.etDateTo.setText("$dayOfMonth-$monthName-$year")
                    }, year, month, day)
                }
            dpd2?.show()
        }

        var dataDB = ArrayList<LogDetail>()
        sendViewModel.logList.observe(this, androidx.lifecycle.Observer { arr ->
            arr?.let {
                dataDB = arr
            }
        })

        val csv =
            Environment.getExternalStorageDirectory().absolutePath + "/test.csv"

        //by Hiting button csv will create inside phone storage.
        binding.btnSend.setOnClickListener{
            sendViewModel.getData(et_dateFrom.text.toString(), et_dateTo.text.toString())
            if (dataDB.size != 0) {
                var writer: CSVWriter? = null
                try {
                    writer = CSVWriter(FileWriter(csv))
                    val data = ArrayList<Array<String>>()
                    data.add(
                        arrayOf(
                            "User Id",
                            "Time 1",
                            "Time 2",
                            "Time 3",
                            "Time 4",
                            "Time 5",
                            "Time 6",
                            "Date"
                        )
                    )
                    for (log in dataDB) {
                        data.add(
                            arrayOf(
                                log.userId.toString(),
                                log.time1,
                                log.time2,
                                log.time3,
                                log.time4,
                                log.time5,
                                log.time6,
                                log.date
                            )
                        )
                    }

                    writer.writeAll(data) // data is adding to csv

                    writer.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                val fileLocation = File(Environment.getExternalStorageDirectory().absolutePath, "test.csv")
                val path = FileProvider.getUriForFile(this.context!!, "com.example.timernav.provider", fileLocation)
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/html")
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("sendTo@email.com"))
                intent.putExtra(Intent.EXTRA_CC, arrayOf<String>("CC@email.com"))
                intent.putExtra(Intent.EXTRA_BCC, arrayOf<String>("BCC@email.com"))
                intent.putExtra(Intent.EXTRA_SUBJECT, "Email Subject")
                intent.putExtra(Intent.EXTRA_STREAM, path)
                startActivity(intent)
            } else {
                Toast.makeText(context, "No Data", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }
}