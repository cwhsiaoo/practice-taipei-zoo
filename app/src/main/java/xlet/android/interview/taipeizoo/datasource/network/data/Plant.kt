package xlet.android.interview.taipeizoo.datasource.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Plant(
    @Json(name = "_id")
    val id: Long,
    @Json(name = "\uFEFFF_Name_Ch")
    val nameCh: String,
    @Json(name = "F_AlsoKnown")
    val alsoKnown: String,
    @Json(name = "F_Location")
    val location: String,
    @Json(name = "F_Name_En")
    val nameEn: String,
    @Json(name = "F_Name_Latin")
    val nameLatin: String,
    @Json(name = "F_Family")
    val family: String,
    @Json(name = "F_Genus")
    val genus: String,
    @Json(name = "F_Brief")
    val brief: String,
    @Json(name = "F_Feature")
    val feature: String,
    @Json(name = "F_Function＆Application")
    val functionApplication: String,
    @Json(name = "F_Pic01_ALT")
    val pic01Alt: String,
    @Json(name = "F_Pic01_URL")
    val pic01Url: String,
    @Json(name = "F_Update")
    val update: String,
)
