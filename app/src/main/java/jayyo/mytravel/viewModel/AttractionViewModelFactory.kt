package jayyo.mytravel.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jayyo.mytravel.data.AttractionRepository

class AttractionViewModelFactory(private val repository: AttractionRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AttractionViewModel(repository) as T
    }
}
