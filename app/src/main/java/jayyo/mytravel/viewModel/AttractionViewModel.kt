package jayyo.mytravel.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jayyo.mytravel.data.AttractionRepository
import jayyo.mytravel.model.Attraction
import kotlinx.coroutines.launch

class AttractionViewModel(private val repository: AttractionRepository) : ViewModel() {

    private val _attractions = MutableLiveData<List<Attraction>>()
    val attractions: LiveData<List<Attraction>> get() = _attractions

    fun getAttractions() {
        viewModelScope.launch {
            try {
                val response = repository.getAttractions()
                _attractions.value = response.data

                println(response.data[1].images[0].src)
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}
