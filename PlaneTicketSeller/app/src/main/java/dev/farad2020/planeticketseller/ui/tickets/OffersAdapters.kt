package dev.farad2020.planeticketseller.ui.tickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.farad2020.data.model.OfferItem
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
//        holder.imageView.setImageResource(item.imageResource)
        holder.title.text = item.title
        holder.city.text = item.city
        holder.price.text = formatNumber(item.price)
    }

    private fun formatNumber(number: Int): String {
        val formattedNumber = number.toString()
        val regex = Regex("""\d{1,3}(?!\d)""")
        return regex.replace(formattedNumber) { matchResult ->
            matchResult.value.reversed().chunked(3).joinToString(separator = " ") { it.reversed() }
        } + "₽"
    }

    override fun getItemCount() = data.size
}

