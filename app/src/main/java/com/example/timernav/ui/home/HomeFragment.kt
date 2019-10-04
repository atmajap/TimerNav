package com.example.timernav.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.timernav.R
import com.example.timerretrofit.DataBaseHandler
import com.example.timerretrofit.Post
import com.example.timerretrofit.User
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var callClicked = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/prajogoatmaja/tugasakhir/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val call = jsonPlaceHolderApi.posts

        root.call_button.setOnClickListener {

            if (callClicked == 0) {
                callClicked = 1
                lap1Result_text?.text = ""
                lap2Result_text?.text = ""
                lap3Result_text?.text = ""
                lap4Result_text?.text = ""
                lap5Result_text?.text = ""
                lap6Result_text?.text = ""
                call.clone().enqueue(object : Callback<List<Post>> {
                    override fun onResponse(
                        call: Call<List<Post>>,
                        response: Response<List<Post>>
                    ) {
                        callClicked = 0

                        if (!response.isSuccessful) {
                            lap1Result_text?.text = "Code : ${response.code()}"
                            lap2Result_text?.text = "Code : ${response.code()}"
                            lap3Result_text?.text = "Code : ${response.code()}"
                            lap4Result_text?.text = "Code : ${response.code()}"
                            lap5Result_text?.text = "Code : ${response.code()}"
                            lap6Result_text?.text = "Code : ${response.code()}"
                            return
                        }

                        val posts = response.body()

                        for (post in posts!!) {
                            when (post.id) {
                                1 -> lap1Result_text?.append(post.time.toString())
                                2 -> lap2Result_text?.append(post.time.toString())
                                3 -> lap3Result_text?.append(post.time.toString())
                                4 -> lap4Result_text?.append(post.time.toString())
                                5 -> lap5Result_text?.append(post.time.toString())
                                6 -> lap6Result_text?.append(post.time.toString())
                                else -> {
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                        callClicked = 0
                        lap1Result_text?.text = t.message
                        lap2Result_text?.text = t.message
                        lap3Result_text?.text = t.message
                        lap4Result_text?.text = t.message
                        lap5Result_text?.text = t.message
                        lap6Result_text?.text = t.message
                    }

                })
            }
        }

        root.save_button.setOnClickListener {
            val context = root.context
            val dialog = AlertDialog.Builder(context)
            val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
            val idNumber = dialogView.findViewById<EditText>(R.id.id_number)
            dialog.setView(dialogView)
            dialog.setCancelable(true)
            dialog.setPositiveButton("validate", { dialogInterface: DialogInterface, i: Int -> })
            val customDialog = dialog.create()
            customDialog.show()
            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                if (idNumber.text.length > 5) {

                    val outputPattern = "dd-MMM-yyyy"
                    val outputFormat = SimpleDateFormat(outputPattern)
                    val date = outputFormat.format(Date())

                    var user = User(
                        userId = idNumber.text.toString().toInt(), time1 = lap1Result_text?.text.toString(),
                        time2 = lap2Result_text?.text.toString(), time3 = lap3Result_text?.text.toString(),
                        time4 = lap4Result_text?.text.toString(), time5 = lap5Result_text?.text.toString(),
                        time6 = lap6Result_text?.text.toString(), date = date
                    )
                    var db = DataBaseHandler()
                    db.insertData(user)
                    customDialog.dismiss()
                } else
                    Toast.makeText(activity?.baseContext, "ID not valid", Toast.LENGTH_SHORT).show()
            }

        }

        return root
    }
}