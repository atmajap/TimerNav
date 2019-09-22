package com.example.timerretrofit

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyDB"
val TABLE_NAME ="Users"
val COL_ID = "id"
val COL_USERID = "userId"
val COL_TIME1 = "time1"
val COL_TIME2 = "time2"
val COL_TIME3 = "time3"
val COL_TIME4 = "time4"
val COL_TIME5 = "time5"
val COL_TIME6 = "time6"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_USERID + " INTEGER," +
                COL_TIME1 + " VARCHAR(256)," +
                COL_TIME2 + " VARCHAR(256)," +
                COL_TIME3 + " VARCHAR(256)," +
                COL_TIME4 + " VARCHAR(256)," +
                COL_TIME5 + " VARCHAR(256)," +
                COL_TIME6 + " VARCHAR(256))";

        db?.execSQL(createTable)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(user : User){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_USERID, user.userId)
        cv.put(COL_TIME1, user.time1)
        cv.put(COL_TIME2, user.time2)
        cv.put(COL_TIME3, user.time3)
        cv.put(COL_TIME4, user.time4)
        cv.put(COL_TIME5, user.time5)
        cv.put(COL_TIME6, user.time6)
        var result = db.insert(TABLE_NAME, null, cv)
        if(result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

    }
    fun readData() : MutableList<User>{
        var list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                var user = User()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.userId = result.getString(result.getColumnIndex(COL_USERID)).toInt()
                user.time1 = result.getString(result.getColumnIndex(COL_TIME1)).toString()
                user.time2 = result.getString(result.getColumnIndex(COL_TIME2)).toString()
                user.time3 = result.getString(result.getColumnIndex(COL_TIME3)).toString()
                user.time4 = result.getString(result.getColumnIndex(COL_TIME4)).toString()
                user.time5 = result.getString(result.getColumnIndex(COL_TIME5)).toString()
                user.time6 = result.getString(result.getColumnIndex(COL_TIME6)).toString()
                list.add(user)

            }while (result.moveToNext())
        }

        result.close()
        db.close()

        return list
    }
}
