package dev.farad2020.planeticketseller.ui.ticketMain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.farad2020.data.model.OfferItem
import dev.farad2020.planeticketseller.R
import dev.farad2020.planeticketseller.databinding.LiItemOfferBinding

class OffersAdapter(private val data: List<OfferItem>)
    : RecyclerView.Adapter<OffersAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(binding: LiItemOfferBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.ivMain
        val title = binding.tvTitle
        val city = binding.tvCity
        val price = binding.tvPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LiItemOfferBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]

//        Might Trigger outOfBounds, should add check later or add to caster
        holder.imageView.setImageResource(images[(item.id - 1) % 3])
        holder.title.text = item.title
        holder.city.text = item.city
        holder.price.text =  "от ${item.getFormattedPrice()}₽"
    }
    
    

    override fun getItemCount() = data.size

    companion object{
        private val images
                = listOf(
            R.drawable.ex_pic_1,
            R.drawable.ex_pic_2,
            R.drawable.ex_pic_3)
    }
}

