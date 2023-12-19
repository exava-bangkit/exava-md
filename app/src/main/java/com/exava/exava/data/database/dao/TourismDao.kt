package com.exava.exava.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.exava.exava.data.model.Tourism

@Dao
interface TourismDao {

    @Query("SELECT * FROM tourism")
    suspend fun getAll(): List<Tourism>

    @Insert
    // TODO : menambahkan tourism (model tourism masih belum ada)
    suspend fun insertTourism(tourism: Tourism)


    @Update
    // TODO : mengeditkan tourism (model tourism masih belum ada)
    suspend fun updateTourism(tourism: Tourism)


}