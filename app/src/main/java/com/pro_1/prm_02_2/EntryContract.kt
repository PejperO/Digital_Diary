package com.pro_1.prm_02_2

import android.provider.BaseColumns

object EntryContract {
    object EntryTable : BaseColumns {
        const val TABLE_NAME = "entries"
        const val COLUMN_TITLE = "title"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_LOCATION = "location"

        const val SQL_CREATE_ENTRIES =
            "CREATE TABLE $TABLE_NAME (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "$COLUMN_TITLE TEXT," +
                    "$COLUMN_CONTENT TEXT," +
                    "$COLUMN_LOCATION TEXT)"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}
