package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.model.Inform

@Database(entities = [Inform::class], version = 1, exportSchema = false)
abstract class InformDatabase: RoomDatabase() {
    abstract val dao: InformDao
}