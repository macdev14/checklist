package pt.ipca.alunos.checklist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO {
    @Insert
    suspend fun insert(item: ItemEntity)

    @Query("SELECT * FROM item_table WHERE id = :id")
    fun getById(id: Int): Flow<ItemEntity>

    @Query("SELECT * FROM item_table")
    fun getAll(): Flow<List<ItemEntity>> // Added to fetch all items

    @Delete
    suspend fun delete(item: ItemEntity)
}