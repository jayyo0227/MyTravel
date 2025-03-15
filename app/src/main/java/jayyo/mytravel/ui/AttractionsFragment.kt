package jayyo.mytravel.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jayyo.mytravel.R
import jayyo.mytravel.ViewModel.AttractionViewModel
import jayyo.mytravel.ViewModel.AttractionViewModelFactory
import jayyo.mytravel.data.AttractionRepository
import jayyo.mytravel.data.RetrofitClient
import jayyo.mytravel.databinding.FragmentAttractionsBinding

class AttractionsFragment : Fragment(R.layout.fragment_attractions) {

    private lateinit var binding: FragmentAttractionsBinding
    private val attractionViewModel: AttractionViewModel by viewModels {
        AttractionViewModelFactory(AttractionRepository(RetrofitClient.apiService))
    }
    private lateinit var attractionsAdapter: AttractionsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAttractionsBinding.bind(view)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        attractionsAdapter = AttractionsAdapter(findNavController())
        recyclerView.adapter = attractionsAdapter

        attractionViewModel.attractions.observe(viewLifecycleOwner, Observer { attractions ->
            attractionsAdapter.submitList(attractions)
        })

        attractionViewModel.getAttractions()

        // 錯誤處理或資料未來可能會顯示訊息
        attractionViewModel.attractions.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                Toast.makeText(context, "No attractions found", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
