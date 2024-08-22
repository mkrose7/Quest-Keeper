@file:Suppress("AndroidUnresolvedRoomSqlReference")

package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountDao {
    @Query("SELECT * FROM count WHERE id = :id")
    fun getCountById(id: Int): Flow<Count>

    @Query("UPDATE count SET points = points + 5 WHERE id = :id")
    suspend fun addPoints(id: Int)
}
