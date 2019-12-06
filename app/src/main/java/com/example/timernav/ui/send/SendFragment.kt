package com.example.timernav.ui.send

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.timernav.databinding.FragmentSendBinding
import com.opencsv.CSVWriter
import java.io.FileWriter
import java.io.IOException


class SendFragment : Fragment() {

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

        val PERMISSIONS = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        // Here, thisActivity is the current activity
        if (Build.VERSION.SDK_INT >= 23) {
            if (context!!.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
            ) {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(activity as Activity, PERMISSIONS, 2)
            } else {
                // Permission has already been granted
            }
        }

        val csv =
            Environment.getExternalStorageDirectory().absolutePath + "/test.csv"

        //by Hiting button csv will create inside phone storage.
        binding.buttonAdd.setOnClickListener(View.OnClickListener {
            var writer: CSVWriter? = null
            try {
                writer = CSVWriter(FileWriter(csv))

                val data = ArrayList<Array<String>>()
                data.add(arrayOf("Country", "Capital"))
                data.add(arrayOf("India", "New Delhi"))
                data.add(arrayOf("United States", "Washington D.C"))
                data.add(arrayOf("Germany", "Berlin"))

                writer.writeAll(data) // data is adding to csv

                writer.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        })

/*        val db = DataBaseHandler()
        val exportDir = File(Environment.getExternalStorageDirectory(), "")
        if(!exportDir.exists()){
            exportDir.mkdirs()
        }
        val file = File(exportDir,  "test.csv")
        try{
            file.createNewFile()
            val csvWrite = CSVWriter(FileWriter(file))

            csvWrite.writeNext("asd")
        }
        val filename = "test.csv"
        val filelocation = File(Environment.getExternalStorageDirectory().absolutePath, filename)
        val path = Uri.fromFile(filelocation)*/
//        val intent = Intent(Intent.ACTION_SEND)
//        intent.setType("text/html")
//        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("sendTo@email.com"))
//        intent.putExtra(Intent.EXTRA_CC, arrayOf<String>("CC@email.com"))
//        intent.putExtra(Intent.EXTRA_BCC, arrayOf<String>("BCC@email.com"))
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Email Subject")
        //intent.putExtra(Intent.EXTRA_STREAM, path)
//        startActivity(intent)
        return view
    }
}