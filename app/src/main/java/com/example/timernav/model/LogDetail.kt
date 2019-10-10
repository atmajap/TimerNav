package com.example.timernav.model

import com.example.timerretrofit.User


data class LogDetail(var id: Int = 0, var userId: Int = 0, var time1: String = "", var time2: String = "",
                var time3: String = "", var time4: String = "", var time5: String= "",
                var time6: String = "", var date: String = "", var sequence: Int = 0) {

    constructor(user: User, sequence: Int) : this() {
        id = user.id
        userId = user.userId
        time1 = user.time1
        time2 = user.time2
        time3 = user.time3
        time4 = user.time4
        time5 = user.time5
        time6 = user.time6
        date = user.date
        this.sequence = sequence
    }
}