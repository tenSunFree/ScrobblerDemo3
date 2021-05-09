package com.example.scrobblerdemo3.home.model

import androidx.room.Dao
import androidx.room.Query
import com.example.scrobblerdemo3.common.local.BaseDao
import com.example.scrobblerdemo3.home.model.HomeBaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HomeDao : BaseDao<HomeBaseEntity.HomeEntity> {
    @Query("SELECT * FROM home")
    abstract fun getData(): Flow<List<HomeBaseEntity.HomeEntity>>
}