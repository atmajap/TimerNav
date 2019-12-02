package com.example.timernav.ui.send

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.timernav.R
import com.example.timerretrofit.DataBaseHandler
import com.opencsv.CSVWriter
import java.io.File
import java.io.FileWriter

class SendFragment : Fragment() {

    private lateinit var sendViewModel: SendViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sendViewModel =
            ViewModelProviders.of(this).get(SendViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_send, container, false)
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
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/html")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("sendTo@email.com"))
        intent.putExtra(Intent.EXTRA_CC, arrayOf<String>("CC@email.com"))
        intent.putExtra(Intent.EXTRA_BCC, arrayOf<String>("BCC@email.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Email Subject")
        //intent.putExtra(Intent.EXTRA_STREAM, path)
        startActivity(intent)
        return root
    }
}