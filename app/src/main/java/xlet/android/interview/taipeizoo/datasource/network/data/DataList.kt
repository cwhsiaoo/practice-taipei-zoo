package xlet.android.interview.taipeizoo.datasource.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataList<T>(
    @Json(name = "result")
    val result: Result<T>,
) {
    fun getResultList(): List<T> {
        return result.results
    }
}

@JsonClass(generateAdapter = true)
data class Result<T>(
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "offset")
    val offset: Int,
    @Json(name = "count")
    val count: Int,
    @Json(name = "results")
    val results: List<T>,
) {
    fun hasNext(): Boolean {
        return offset + limit < count
    }

    fun nextOffset(): Int {
        return offset + limit
    }
}
