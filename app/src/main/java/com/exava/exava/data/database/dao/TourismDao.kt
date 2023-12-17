package com.exava.exava.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

@Dao
interface TourismDao {

    @Insert
    // TODO : menambahkan tourism (model tourism masih belum ada)
    suspend fun insertTourism()


    @Update
    // TODO : mengeditkan tourism (model tourism masih belum ada)
    suspend fun updateTourism()


}