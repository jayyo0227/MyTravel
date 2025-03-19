package jayyo.mytravel.model

import com.google.gson.annotations.SerializedName

data class Attraction(
    @SerializedName("name")
    val name: String,

    @SerializedName("tel")
    val tel: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("introduction")
    val introduction: String,

    @SerializedName("official_site")
    val officialSite: String,

    @SerializedName("modified")
    val modified: String,

    @SerializedName("images")
    val images: List<Image>,
)

