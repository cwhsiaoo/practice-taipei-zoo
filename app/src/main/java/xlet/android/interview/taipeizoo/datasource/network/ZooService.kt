package xlet.android.interview.taipeizoo.datasource.network

import retrofit2.http.GET
import retrofit2.http.Query
import xlet.android.interview.taipeizoo.datasource.network.data.DataList
import xlet.android.interview.taipeizoo.datasource.network.data.Location
import xlet.android.interview.taipeizoo.datasource.network.data.Plant

interface ZooService {
    @GET("/api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14")
    suspend fun getPlantInfo(
        @Query("scope") scope: String = "resourceAquire",
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): DataList<Plant>

    @GET("/api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    suspend fun getLocationInfo(
        @Query("scope") scope: String = "resourceAquire",
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): DataList<Location>
}