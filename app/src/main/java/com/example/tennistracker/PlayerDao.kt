package com.example.tennistracker
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlayer(player: Player)

    @Query("SELECT * FROM player ORDER BY points ASC")
    fun getPlayerPointsOrdered(): Flow<List<Player>>

}