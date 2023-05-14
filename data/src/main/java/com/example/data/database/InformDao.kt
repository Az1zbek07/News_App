package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.Inform
import kotlinx.coroutines.flow.Flow

@Dao
interface InformDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveNew(inform: Inform)

    @Query("SELECT * FROM Inform ORDER BY roomId DESC")
    fun getLocalNews(): Flow<List<Inform>>
}