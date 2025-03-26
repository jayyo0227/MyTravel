package jayyo.mytravel.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jayyo.mytravel.R
import jayyo.mytravel.viewModel.AttractionViewModel
import jayyo.mytravel.viewModel.AttractionViewModelFactory
import jayyo.mytravel.data.AttractionRepository
import jayyo.mytravel.data.Detail
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

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_languages -> {
                        showLanguages()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showLanguages() {
        val languages =
            arrayOf("繁體中文", "簡體中文", "English", "日本語", "한국어", "es", "th", "vi")

        AlertDialog.Builder(requireContext())
            .setItems(languages) { dialog, which ->
                when (which) {
                    0 -> Detail.language = "zh-tw"
                    1 -> Detail.language = "zh-cn"
                    2 -> Detail.language = "en"
                    3 -> Detail.language = "ja"
                    4 -> Detail.language = "ko"
                    5 -> Detail.language = "es"
                    6 -> Detail.language = "th"
                    7 -> Detail.language = "vi"
                }

                attractionViewModel.getAttractions()

                dialog.dismiss()
            }
            .show()
    }
}
