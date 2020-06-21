package com.example.storage

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class AppDatabaseCallback : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
    }
}