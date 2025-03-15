package jayyo.mytravel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import jayyo.mytravel.R
import jayyo.mytravel.data.Detail
import jayyo.mytravel.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentDetailBinding.bind(view)

//        binding.detailName.text = Detail.name
//        binding.detailIntroduction.text = Detail.introduction
//        binding.detailOfficialSite.text = Detail.officialSite
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        view.findViewById<TextView>(R.id.detail_name).text = Detail.name
        view.findViewById<TextView>(R.id.detail_introduction).text = Detail.introduction
        view.findViewById<TextView>(R.id.detail_official_site).text = Detail.officialSite

        return view
    }
}
