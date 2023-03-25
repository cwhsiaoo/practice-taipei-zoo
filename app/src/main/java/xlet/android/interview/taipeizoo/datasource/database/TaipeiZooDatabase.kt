package xlet.android.interview.taipeizoo.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import xlet.android.interview.taipeizoo.datasource.database.dao.TaipeiZooDao
import xlet.android.interview.taipeizoo.datasource.database.table.Location
import xlet.android.interview.taipeizoo.datasource.database.table.Plant

@Database(
    entities = [
        Plant::class, Location::class,
    ],
    version = 1,
)
abstract class TaipeiZooDatabase : RoomDatabase() {

    abstract fun taipeiZooDao(): TaipeiZooDao
}