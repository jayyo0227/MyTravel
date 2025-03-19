package jayyo.mytravel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import jayyo.mytravel.R
import jayyo.mytravel.data.Detail
import jayyo.mytravel.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        binding = FragmentDetailBinding.bind(view)

        binding.attraction = Detail.attraction

        if (Detail.attraction.images.isNotEmpty()) {
            Picasso.get()
                .load(Detail.attraction.images[0].src.ifEmpty { null })
                .placeholder(R.drawable.empty)
                .error(R.drawable.empty)
                .into(binding.detailImage)
        }

        return view
    }
}
