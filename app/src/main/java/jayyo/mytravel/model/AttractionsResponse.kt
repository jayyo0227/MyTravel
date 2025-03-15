package jayyo.mytravel.model

import com.google.gson.annotations.SerializedName

data class AttractionsResponse(
    @SerializedName("total")
    val total: Int,

    @SerializedName("data")
    val data: List<Attraction>
)
