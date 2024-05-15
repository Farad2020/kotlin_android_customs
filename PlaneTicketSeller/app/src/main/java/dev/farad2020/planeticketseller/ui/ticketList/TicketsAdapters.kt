package dev.farad2020.planeticketseller.ui.ticketList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.farad2020.data.model.OfferItem
import dev.farad2020.data.model.TicketItem
import dev.farad2020.planeticketseller.R
import dev.farad2020.planeticketseller.databinding.LiItemOfferBinding
import dev.farad2020.planeticketseller.databinding.LiTicketBinding
import dev.farad2020.planeticketseller.ui.base.visible


//TODO add margin when badge visible

class TicketsAdapters(
    private val data: List<TicketItem>,
    private val maxVisibleItems: Int
)
    : RecyclerView.Adapter<TicketsAdapters.ItemViewHolder>() {

    inner class ItemViewHolder(binding: LiTicketBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val badge = binding.tvSpecial
        val price = binding.tvPrice

        val departureDate = binding.tvStartTime
        val departureAirport = binding.tvStartCode
        val arrivalDate = binding.tvEndTime
        val arrivalAirport = binding.tvEndCode

        val flightLength = binding.tvFlightTime
        val flightInfo = binding.tvFlightInfo
        val flightInfoDivider = binding.tvFlightInfoDivider
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

    private fun showFlightInfo(isShow: Boolean, holder: ItemViewHolder){
        holder.flightInfoDivider.visible(isShow)
        holder.flightInfo.visible(isShow)
    }
    
    

    override fun getItemCount() = data.size
}

