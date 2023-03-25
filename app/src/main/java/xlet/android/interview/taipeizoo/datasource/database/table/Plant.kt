package xlet.android.interview.taipeizoo.datasource.database.table

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @Embedded(prefix = "name")
    val name: I18N,
    @ColumnInfo(name = "alsoKnown")
    val alsoKnown: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "family")
    val family: String,
    @ColumnInfo(name = "genus")
    val genus: String,
    @ColumnInfo(name = "brief")
    val brief: String,
    @ColumnInfo(name = "feature")
    val feature: String,
    @ColumnInfo(name = "function")
    val functionApplication: String,
    @Embedded(prefix = "pic01")
    val pic01: Pic,
    @ColumnInfo(name = "F_Update")
    val update: String,
)

data class I18N(
    val en: String,
    val zh: String,
    val latin: String,
)

data class Pic(
    val alt: String,
    val url: String,
)
