package com.example.timerretrofit

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.timernav.utils.AppAplication.Companion.context

class DataBaseHandler : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
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
        val COL_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_USERID + " INTEGER," +
                COL_TIME1 + " VARCHAR(256)," +
                COL_TIME2 + " VARCHAR(256)," +
                COL_TIME3 + " VARCHAR(256)," +
                COL_TIME4 + " VARCHAR(256)," +
                COL_TIME5 + " VARCHAR(256)," +
                COL_TIME6 + " VARCHAR(256)," +
                COL_DATE + " VARCHAR(256))"
        db?.execSQL(createTable)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(user : User) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_USERID, user.userId)
        cv.put(COL_TIME1, user.time1)
        cv.put(COL_TIME2, user.time2)
        cv.put(COL_TIME3, user.time3)
        cv.put(COL_TIME4, user.time4)
        cv.put(COL_TIME5, user.time5)
        cv.put(COL_TIME6, user.time6)
        cv.put(COL_DATE, user.date)
        var result = db.insert(TABLE_NAME, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun getAllDateData(): ArrayList<String> {
        val db = this.writableDatabase
        var arrayOfDate = arrayListOf<String>()
        val result = db.rawQuery("SELECT $COL_DATE FROM $TABLE_NAME GROUP BY $COL_DATE", null)
        result?.let {
            if (result.count != 0) {
                it.moveToFirst()?.let {
                    do {
                        arrayOfDate.add(result.getString(result.getColumnIndex(COL_DATE)))
                    } while (result.moveToNext())
                }
            }
        }
        return arrayOfDate
    }

    fun getAllUserData(): ArrayList<String> {
        val db = this.writableDatabase
        var arrayOfId = arrayListOf<String>()
        val result = db.rawQuery("SELECT $COL_USERID FROM $TABLE_NAME GROUP BY $COL_USERID", null)
        result?.let {
            if (result.count != 0) {
                it.moveToFirst()?.let {
                    do {
                        arrayOfId.add(result.getString(result.getColumnIndex(COL_USERID)))
                    } while (result.moveToNext())
                }
            }
        }
        return arrayOfId
    }

    fun getUserData(date: String): ArrayList<User> {
        val db = this.writableDatabase
        var arrayOfUser = arrayListOf<User>()
        val result = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COL_DATE = '$date' ORDER BY $COL_USERID",null)
        result?.let {
            if (result.count != 0) {
                it.moveToFirst()?.let {
                    do {
                        val id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                        val userId = result.getString(result.getColumnIndex(COL_USERID)).toInt()
                        val time1 = result.getString(result.getColumnIndex(COL_TIME1)).toString()
                        val time2 = result.getString(result.getColumnIndex(COL_TIME2)).toString()
                        val time3 = result.getString(result.getColumnIndex(COL_TIME3)).toString()
                        val time4 = result.getString(result.getColumnIndex(COL_TIME4)).toString()
                        val time5 = result.getString(result.getColumnIndex(COL_TIME5)).toString()
                        val time6 = result.getString(result.getColumnIndex(COL_TIME6)).toString()
                        val date = result.getString(result.getColumnIndex(COL_DATE)).toString()
                        var user = User(
                            id = id, userId = userId, time1 = time1, time2 = time2, time3 = time3,
                            time4 = time4, time5 = time5, time6 = time6, date = date
                        )
                        arrayOfUser.add(user)
                    } while (result.moveToNext())
                }
            }
        }
        return arrayOfUser
    }

        fun getDateData(userId: String): ArrayList<User> {
            val db = this.writableDatabase
            var arrayOfUser = arrayListOf<User>()
            val result = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COL_USERID = $userId", null)
            result?.let {
                if (result.count != 0) {
                    it.moveToFirst()?.let {
                        do {
                            val id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                            val userId = result.getString(result.getColumnIndex(COL_USERID)).toInt()
                            val time1 =
                                result.getString(result.getColumnIndex(COL_TIME1)).toString()
                            val time2 =
                                result.getString(result.getColumnIndex(COL_TIME2)).toString()
                            val time3 =
                                result.getString(result.getColumnIndex(COL_TIME3)).toString()
                            val time4 =
                                result.getString(result.getColumnIndex(COL_TIME4)).toString()
                            val time5 =
                                result.getString(result.getColumnIndex(COL_TIME5)).toString()
                            val time6 =
                                result.getString(result.getColumnIndex(COL_TIME6)).toString()
                            val date = result.getString(result.getColumnIndex(COL_DATE)).toString()
                            var user = User(
                                id = id,
                                userId = userId,
                                time1 = time1,
                                time2 = time2,
                                time3 = time3,
                                time4 = time4,
                                time5 = time5,
                                time6 = time6,
                                date = date
                            )
                            arrayOfUser.add(user)
                        } while (result.moveToNext())
                    }
                }
            }
            return arrayOfUser
    }
}
