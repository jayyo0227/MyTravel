package jayyo.mytravel.data

import jayyo.mytravel.model.AttractionsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @GET("{language}/Attractions/All")
    @Headers("Accept: application/json")
    suspend fun getAttractions(
        @Path("language") language: String
    ): AttractionsResponse
}
