package com.example.timernav.ui.send

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timernav.model.LogDetail
import com.example.timerretrofit.DataBaseHandler

class SendViewModel : ViewModel() {

    var date = MutableLiveData<String>()
    var logList = MutableLiveData<ArrayList<LogDetail>>()
    var tempLogList = ArrayList<LogDetail>()

    fun getData(date1: String, date2: String) {
//        this.date.value = date
        var db = DataBaseHandler()
        val userList = db.getDataByDate(date1, date2)
        var tempId = 0
        var sequence = 1

        if (userList.size != 0) {
            for (i in 0 until userList.size) {
                if (userList[i].userId != tempId) {
                    tempId = userList[i].userId
                    sequence = 1
                }
                tempLogList.add(LogDetail(userList[i], sequence))
                sequence += 1
            }
            logList.value = tempLogList
        }
    }
}