package dev.farad2020.planeticketseller.ui.ticketList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.farad2020.data.model.OfferItem
import dev.farad2020.data.model.TicketItem
import dev.farad2020.planeticketseller.R
import dev.farad2020.planeticketseller.databinding.LiItemOfferBinding
import dev.farad2020.planeticketseller.databinding.LiTicketBinding


//TODO add margin when badge visible

class TicketsAdapters(
    private val data: List<TicketItem>,
    private val maxVisibleItems: Int
)
    : RecyclerView.Adapter<TicketsAdapters.ItemViewHolder>() {

    inner class ItemViewHolder(binding: LiTicketBinding)
        : RecyclerView.ViewHolder(binding.root) {
//        val ivState = binding.ivState
//        val title = binding.tvTitle
//        val time = binding.tvSubtitle
//        val price = binding.tvPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LiTicketBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]

//        Might Trigger outOfBounds, should add check later or add to caster
//        holder.imageView.setImageResource(images[(item.id - 1) % 3])
//        holder.title.text = item.title
//        holder.city.text = item.city
//
//        holder.price.text =  "от ${addSpacesFromEnd(item.price.toString())}₽"
    }
    
    

    override fun getItemCount() = data.size
}

