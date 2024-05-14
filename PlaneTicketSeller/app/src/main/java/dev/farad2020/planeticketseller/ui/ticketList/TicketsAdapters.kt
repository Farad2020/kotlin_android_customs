package dev.farad2020.planeticketseller.ui.ticketList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.farad2020.data.model.OfferItem
import dev.farad2020.planeticketseller.R
import dev.farad2020.planeticketseller.databinding.LiItemOfferBinding


//TODO add margin when badge visible

class TicketsAdapters(private val data: List<OfferItem>)
    : RecyclerView.Adapter<TicketsAdapters.ItemViewHolder>() {

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
//        holder.imageView.setImageResource(images[(item.id - 1) % 3])
        holder.title.text = item.title
        holder.city.text = item.city

        holder.price.text =  "от ${addSpacesFromEnd(item.price.toString())}₽"
    }

    fun addSpacesFromEnd(text: String): String {
        val reversed = text.reversed()
        val stringBuilder = StringBuilder()
        for (i in reversed.indices) {
            stringBuilder.append(reversed[i])
            if (i > 2 && (i + 1) % 4 == 0) {
                stringBuilder.append(' ')
            }
        }
        return stringBuilder.reversed().toString()
    }
    
    

    override fun getItemCount() = data.size
}

