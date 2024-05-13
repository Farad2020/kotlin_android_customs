package dev.farad2020.planeticketseller.ui.btm_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.farad2020.data.model.PopularPlaceItem
import dev.farad2020.planeticketseller.databinding.LiItemCityBinding

class PlacesAdapter(private val data: List<PopularPlaceItem>)
    : RecyclerView.Adapter<PlacesAdapter.ItemViewHolder>() {



    inner class ItemViewHolder(binding: LiItemCityBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.ivMain
        val title = binding.tvTitle
        val subtitle = binding.tvSubtitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LiItemCityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
//        holder.imageView.setImageResource(item.imageResource)
        holder.title.text = item.title
        holder.subtitle.text =  "Популярное направление"
    }


    override fun getItemCount() = data.size
}