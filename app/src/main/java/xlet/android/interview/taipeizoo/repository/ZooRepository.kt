package xlet.android.interview.taipeizoo.repository

import androidx.room.withTransaction
import kotlinx.coroutines.flow.Flow
import xlet.android.interview.taipeizoo.datasource.database.TaipeiZooDatabase
import xlet.android.interview.taipeizoo.datasource.database.table.I18N
import xlet.android.interview.taipeizoo.datasource.database.table.Location
import xlet.android.interview.taipeizoo.datasource.database.table.Pic
import xlet.android.interview.taipeizoo.datasource.database.table.Plant
import xlet.android.interview.taipeizoo.datasource.network.ZooService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ZooRepository @Inject constructor(
    private val database: TaipeiZooDatabase,
    private val zooService: ZooService,
) {
    private val dao = database.taipeiZooDao()

    fun getPlantsByLocationName(name: String): Flow<List<Plant>> {
        return dao.getPlantsByLocation(name)
    }

    fun getPlant(id: Long): Flow<Plant> {
        return dao.getPlant(id)
    }

    fun getLocation(id: Long): Flow<Location> {
        return dao.getLocation(id)
    }

    fun getAllLocations(): Flow<List<Location>> {
        return dao.getAllLocations()
    }

    suspend fun syncLocations() {
        val newData = kotlin.runCatching {
            zooService.getLocationInfo()
        }.getOrElse {
            throw it
        }
        database.withTransaction {
            val dbEntities = newData.getResultList().map {
                Location(
                    id = it.id,
                    category = it.category,
                    name = it.name,
                    picUrl = it.picUrl,
                    info = it.info,
                    url = it.url,
                )
            }
            dao.replaceInsertLocation(dbEntities)
        }
    }

    suspend fun syncPlants() {
        val newData = kotlin.runCatching {
            zooService.getPlantInfo(limit = 220)
        }.getOrElse {
            throw it
        }
        database.withTransaction {
            val dbEntities = newData.getResultList().map {
                Plant(
                    id = it.id,
                    name = I18N(
                        zh = it.nameCh,
                        en = it.nameEn,
                        latin = it.nameLatin,
                    ),
                    alsoKnown = it.alsoKnown,
                    location = it.location,
                    family = it.family,
                    genus = it.genus,
                    brief = it.brief,
                    feature = it.feature,
                    functionApplication = it.functionApplication,
                    pic01 = Pic(
                        alt = it.pic01Alt, url = it.pic01Url,
                    ),
                    update = it.update,
                )
            }
            dao.replaceInsertPlant(dbEntities)
        }
    }
}