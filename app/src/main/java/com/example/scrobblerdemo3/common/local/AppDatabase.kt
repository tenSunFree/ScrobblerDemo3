package com.example.scrobblerdemo3.common.local

import android.content.Context
import androidx.room.*
import com.example.scrobblerdemo3.home.model.HomeDao
import com.example.scrobblerdemo3.home.model.HomeBaseEntity
import dev.matrix.roomigrant.GenerateRoomMigrations

@Database(
    entities = [
        HomeBaseEntity.HomeEntity::class
    ], version = 53
)
@Suppress("TooManyFunctions")
@GenerateRoomMigrations
abstract class AppDatabase : RoomDatabase() {
    abstract fun homeDao(): HomeDao
}

@Suppress("SpreadOperator")
fun provideDatabase(context: Context) = Room
    .databaseBuilder(context, AppDatabase::class.java, "lastfm")
    .addMigrations(*AppDatabase_Migrations.build())
    .build()