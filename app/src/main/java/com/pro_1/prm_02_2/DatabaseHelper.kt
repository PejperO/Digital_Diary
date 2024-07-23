package com.pro_1.prm_02_2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "EntriesDatabase.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(EntryContract.EntryTable.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(EntryContract.EntryTable.SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}
