package com.example.fastcampcalculator

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fastcampcalculator.dao.HistoryDao
import com.example.fastcampcalculator.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun historyDao() : HistoryDao

}