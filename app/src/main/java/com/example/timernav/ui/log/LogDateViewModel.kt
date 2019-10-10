package com.example.timernav.ui.log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timerretrofit.DataBaseHandler

class LogDateViewModel : ViewModel() {
    var dateList = MutableLiveData<ArrayList<String>>()

    fun getDateData() {
        var db = DataBaseHandler()
        dateList.value = db.getAllDateData()
    }
}
