package jayyo.mytravel.data

import jayyo.mytravel.model.AttractionsResponse

class AttractionRepository(private val apiService: ApiService) {

    suspend fun getAttractions(): AttractionsResponse {
        return apiService.getAttractions(Detail.language)
    }
}
