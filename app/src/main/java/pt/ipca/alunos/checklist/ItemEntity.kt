package pt.ipca.alunos.checklist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="item_table")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name="name") val name:String
)
