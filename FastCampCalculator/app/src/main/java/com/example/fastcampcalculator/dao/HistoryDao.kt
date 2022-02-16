package com.example.fastcampcalculator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fastcampcalculator.model.History

@Dao
interface HistoryDao {

    @Query("Select * FROM history")
    fun getAll() : List<History>

    @Insert
    fun insertHistory(history: History)

    @Query("DELETE FROM history")
    fun deleteAll()

}