package com.example.notes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MySQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.w(
            MySQLiteHelper::class.java.name,
            "Upgrading database from version" + oldVersion + " to "
        + newVersion + ", which will destroy all old data")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EDIT_TEXT")

        onCreate(db)
    }


    companion object {
        val TABLE_EDIT_TEXT = "edit_text"
        val COLUMN_ID = "_id"
        val COLUMN_TEXT = "text"
        private val DATABASE_NAME = "NOTES_DATABASE.db"
        private val DATABASE_VERSION = 1
        private val DATABASE_CREATE = ("create table "
                + TABLE_EDIT_TEXT + "(" + COLUMN_ID
                + " integer primary key autoincrement, " + COLUMN_TEXT + " text);")
    }
}