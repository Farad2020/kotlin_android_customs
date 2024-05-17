package dev.farad2020.planeticketseller.ui.ticketList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.farad2020.data.model.TicketItem
import dev.farad2020.planeticketseller.databinding.LiTicketBinding
import dev.farad2020.planeticketseller.ui.base.formatTime24H
import dev.farad2020.planeticketseller.ui.base.gone
import dev.farad2020.planeticketseller.ui.base.visible
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs


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

        holder.badge.gone(item.badge == null)
        holder.badge.text = item.badge ?: ""

        holder.price.text = item.price.getFormattedPrice() // Assuming getFormattedPrice() exists

// Departure and arrival data
        holder.departureDate.text = formatTime24H(item.departure.date)
        holder.departureAirport.text = item.departure.airport
        holder.arrivalDate.text = formatTime24H(item.arrival.date)
        holder.arrivalAirport.text = item.arrival.airport

        showFlightInfo(holder, !item.hasTransfer)

// Handle optional flight length based on data availability
        holder.flightLength.text = calculateTravelTime(item.departure.date, item.arrival.date)
    }

    private fun showFlightInfo(holder: ItemViewHolder, isShow: Boolean){
        holder.flightInfoDivider.visible(isShow)
        holder.flightInfo.visible(isShow)
    }

    private fun calculateTravelTime(arrivalDateTime: String, departureDateTime: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

        val arrivalDate = dateFormat.parse(arrivalDateTime)
        val departureDate = dateFormat.parse(departureDateTime)


        val timeDifference = abs(departureDate.time - arrivalDate.time) // Difference in milliseconds

        // Convert milliseconds to hours
        val hours = timeDifference.toDouble() / (1000 * 60 * 60)

        return "${"%.1f".format(hours)}ч в пути"
    }
    
    

    override fun getItemCount() = data.size
}

