package com.example.timernav.ui.log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timerretrofit.DataBaseHandler

class LogUserViewModel : ViewModel() {

    var userList = MutableLiveData<ArrayList<String>>()

    fun getUserData() {
        var db = DataBaseHandler()
        userList.value = db.getAllUserData()
    }
}
