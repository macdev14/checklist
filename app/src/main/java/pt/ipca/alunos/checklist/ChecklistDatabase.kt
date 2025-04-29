package pt.ipca.alunos.checklist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ItemEntity::class], version = 1)
abstract class ChecklistDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDAO

    companion object {
        @Volatile
        private var INSTANCE: ChecklistDatabase? = null

        fun getDatabase(context: Context): ChecklistDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChecklistDatabase::class.java,
                    "checklist-db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}