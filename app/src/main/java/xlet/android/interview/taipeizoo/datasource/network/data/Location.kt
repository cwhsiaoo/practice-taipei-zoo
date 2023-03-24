package xlet.android.interview.taipeizoo.datasource.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "_id")
    val id: Long,
    @Json(name = "e_no")
    val no: String,
    @Json(name = "e_category")
    val category: String,
    @Json(name = "e_name")
    val name: String,
    @Json(name = "e_pic_url")
    val picUrl: String,
    @Json(name = "e_info")
    val info: String,
    @Json(name = "e_geo")
    val geo: String,
    @Json(name = "e_url")
    val url: String,
)
