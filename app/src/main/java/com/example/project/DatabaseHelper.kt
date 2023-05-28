package com.example.project

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserManager.db"
        const val TABLE_USER = "user"
        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_USER_NAME = "user_name"
        const val COLUMN_USER_PASSWORD = "user_password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT" + ")")
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }
    fun addUser(user: User): Boolean {
        val db = this.readableDatabase

        // Check if user already exists
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USER WHERE $COLUMN_USER_NAME = ?",
            arrayOf(user.name)
        )

        if (cursor.moveToFirst()) {
            cursor.close()
            db.close()
            return false // User already exists
        }

        cursor.close()

        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_PASSWORD, user.password)

        db.insert(TABLE_USER, null, values)
        db.close()

        return true // User successfully added
    }


    @SuppressLint("Range")
    fun checkUser(name: String, password: String): Int {
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USER WHERE $COLUMN_USER_NAME = ?",
            arrayOf(name)
        )

        if (cursor.moveToFirst()) {
            // User exists
            val storedPassword = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD))
            cursor.close()
            db.close()

            return if (storedPassword == password) {
                2 // Password is correct
            } else {
                1 // Password is incorrect
            }
        }

        cursor.close()
        db.close()

        return 0 // User does not exist
    }
}
