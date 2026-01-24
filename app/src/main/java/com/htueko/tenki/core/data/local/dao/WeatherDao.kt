package com.htueko.tenki.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.htueko.tenki.core.constant.LocalConstant
import com.htueko.tenki.core.data.local.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertWeather(weatherEntity: WeatherEntity)

    @Update
    suspend fun updateWeather(weatherEntity: WeatherEntity)

    @Upsert
    suspend fun upsertWeather(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather_table")
    fun getAllWeather(): Flow<List<WeatherEntity>>

    @Query("SELECT * FROM ${LocalConstant.WEATHER_TABLE_NAME} ORDER BY lastUpdatedEpoch ASC")
    suspend fun getAllWeatherOrderByLastUpdatedAsc(): Flow<List<WeatherEntity>>

    @Delete
    suspend fun deleteWeather(weatherEntity: WeatherEntity)

    @Query("DELETE FROM weather_table WHERE id = :id")
    suspend fun deleteWeatherById(id: Long)

    @Query("DELETE FROM ${LocalConstant.WEATHER_TABLE_NAME} WHERE location = :location")
    suspend fun deleteWeatherByLocation(location: String)

    @Query("SELECT * FROM ${LocalConstant.WEATHER_TABLE_NAME} WHERE location = :location")
    suspend fun getWeatherByLocation(location: String): WeatherEntity?

}