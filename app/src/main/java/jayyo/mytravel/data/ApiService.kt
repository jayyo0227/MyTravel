package jayyo.mytravel.data

import jayyo.mytravel.model.AttractionsResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @GET("Attractions/All")
    @Headers("Accept: application/json")
    suspend fun getAttractions(): AttractionsResponse
}
