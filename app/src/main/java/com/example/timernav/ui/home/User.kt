package com.example.timerretrofit

class User{
    var id : Int = 0
    var userId : Int = 0
    var time1 : String = ""
    var time2 : String = ""
    var time3 : String = ""
    var time4 : String = ""
    var time5 : String = ""
    var time6 : String = ""

    constructor(userId:Int, time1:String, time2:String, time3:String,
                time4:String, time5:String, time6:String){
        this.userId = userId
        this.time1 = time1
        this.time2 = time2
        this.time3 = time3
        this.time4 = time4
        this.time5 = time5
        this.time6 = time6
    }

    constructor(){

    }
}