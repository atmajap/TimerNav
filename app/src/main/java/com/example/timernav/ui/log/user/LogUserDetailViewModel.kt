package com.example.timernav.ui.log.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timernav.model.LogDetail
import com.example.timerretrofit.DataBaseHandler

class LogUserDetailViewModel : ViewModel() {

    var userId = MutableLiveData<String>()
    var logList = MutableLiveData<ArrayList<LogDetail>>()
    var tempLogList = ArrayList<LogDetail>()

    fun getUserData(id: String) {
        userId.value = id
        var db = DataBaseHandler()
        val userList = db.getDateData(id)
        var tempDate = ""
        var sequence = 1

        for (i in 0 until userList.size) {
            if (userList[i].date != tempDate) {
                tempDate = userList[i].date
                sequence = 1
            }
            tempLogList.add(LogDetail(userList[i], sequence))
            sequence += 1
        }
        logList.value = tempLogList
    }
}