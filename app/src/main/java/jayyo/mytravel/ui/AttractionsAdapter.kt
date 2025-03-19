package jayyo.mytravel.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jayyo.mytravel.R
import jayyo.mytravel.data.Detail
import jayyo.mytravel.databinding.ItemAttractionBinding
import jayyo.mytravel.model.Attraction

class AttractionsAdapter(private val navController: NavController) :
    ListAdapter<Attraction, AttractionsAdapter.AttractionViewHolder>(AttractionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val binding =
            ItemAttractionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttractionViewHolder(binding, navController)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        val attraction = getItem(position)
        holder.bind(attraction)
    }

    class AttractionViewHolder(
        private val binding: ItemAttractionBinding,
        private val navController: NavController
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(attraction: Attraction) {
            binding.attraction = attraction
            binding.itemLayout.setOnClickListener {
                Detail.attraction = attraction

                navController.navigate(R.id.action_attractionsFragment_to_detailFragment)
            }

            if (attraction.images.isNotEmpty()) {
                Picasso.get()
                    .load(attraction.images[0].src.ifEmpty { null })
                    .placeholder(R.drawable.empty)
                    .error(R.drawable.empty)
                    .into(binding.image)
            }

            binding.executePendingBindings()
        }
    }

    class AttractionDiffCallback : DiffUtil.ItemCallback<Attraction>() {
        override fun areItemsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem == newItem
        }
    }
}
