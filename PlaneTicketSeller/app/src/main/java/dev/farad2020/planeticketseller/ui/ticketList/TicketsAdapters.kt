package dev.farad2020.planeticketseller.ui.ticketList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.farad2020.data.model.OfferItem
import dev.farad2020.data.model.TicketItem
import dev.farad2020.planeticketseller.R
import dev.farad2020.planeticketseller.databinding.LiItemOfferBinding
import dev.farad2020.planeticketseller.databinding.LiTicketBinding
import dev.farad2020.planeticketseller.ui.base.gone
import dev.farad2020.planeticketseller.ui.base.visible


//TODO add margin when badge visible

class TicketsAdapters(
    private val data: List<TicketItem>
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

        holder.badge.gone(item.badge != null)
        holder.badge.text = item.badge ?: ""

        holder.price.text = item.price.getFormattedPrice() // Assuming getFormattedPrice() exists

// Departure and arrival data
        holder.departureDate.text = item.departure.date
        holder.departureAirport.text = item.departure.airport
        holder.arrivalDate.text = item.arrival.date
        holder.arrivalAirport.text = item.arrival.airport

// Flight information
//        val flightInfoText = getFlightInfoString(item)
        val flightInfoText = "info example"
        holder.flightInfo.text = flightInfoText

// Handle optional flight length based on data availability
        holder.flightLength.text = "add flight exchange"
    }

    private fun handleBadge(holder: ItemViewHolder, text: String = "" ){
        val isGone: Boolean = text.isEmpty()

        if(!isGone){
            holder.badge.text = text
        }

        holder.badge.gone(isGone)
    }

    private fun showFlightInfo(holder: ItemViewHolder, isShow: Boolean){
        holder.flightInfoDivider.visible(isShow)
        holder.flightInfo.visible(isShow)
    }
    
    

    override fun getItemCount() = data.size
}

