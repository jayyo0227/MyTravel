package jayyo.mytravel.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("src")
    val src: String,
)