package xlet.android.interview.taipeizoo.datasource.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xlet.android.interview.taipeizoo.datasource.database.table.Location
import xlet.android.interview.taipeizoo.datasource.database.table.Plant

@Dao
interface TaipeiZooDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replaceInsertPlant(list: List<Plant>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replaceInsertLocation(list: List<Location>): List<Long>

    @Query(
        """
        SELECT * FROM plants WHERE location LIKE :location
        """
    )
    fun getPlantsByLocation(location: String): Flow<List<Plant>>

    @Query(
        """
        SELECT * FROM plants where id = :id
        """
    )
    fun getPlant(id: Long): Flow<Plant>

    @Query(
        """
        SELECT * FROM locations
        """
    )
    fun getAllLocations(): Flow<List<Location>>

    @Query(
        """
        SELECT * FROM locations where id = :id
        """
    )
    fun getLocation(id: Long): Flow<Location>

    @Ignore
    fun getPlantsByLocation(location: Location): Flow<List<Plant>> {
        return getPlantsByLocation(location.name)
    }
}