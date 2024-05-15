package dev.farad2020.planeticketseller.ui.destination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.farad2020.data.model.TicketItem
import dev.farad2020.data.model.TicketOfferItem
import dev.farad2020.planeticketseller.R
import dev.farad2020.planeticketseller.databinding.LiTicketBinding
import dev.farad2020.planeticketseller.databinding.LiTicketOfferBinding


//TODO add margin when badge visible

class TicketOffersAdapter(
    private val data: List<TicketOfferItem>,
    private val maxVisibleItems: Int
)
    : RecyclerView.Adapter<TicketOffersAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(binding: LiTicketOfferBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val ivState = binding.ivState
        val title = binding.tvTitle
        val time = binding.tvSubtitle
        val price = binding.tvPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LiTicketOfferBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.ivState.setImageResource(stateIcons[position % 3])
        holder.title.text = item.title
        holder.time.text = item.timeRange.joinToString (separator = " ")
        holder.price.text =  "${item.price.value} ₽"
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

    companion object{
        private val stateIcons
                = listOf(
            R.drawable.ic_circle_red,
            R.drawable.ic_circle_blue,
            R.drawable.ic_circle_white)
    }
}

