package xlet.android.interview.taipeizoo.datasource.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "pic_url")
    val picUrl: String,
    @ColumnInfo(name = "info")
    val info: String,
    @ColumnInfo(name = "url")
    val url: String,
)
