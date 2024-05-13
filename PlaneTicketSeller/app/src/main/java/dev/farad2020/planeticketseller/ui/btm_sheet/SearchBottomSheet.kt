package dev.farad2020.planeticketseller.ui.btm_sheet

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dev.farad2020.planeticketseller.R
import dev.farad2020.planeticketseller.databinding.BtmSheetSearchBinding
import dev.farad2020.planeticketseller.ui.tickets.OffersAdapter
import dev.farad2020.planeticketseller.ui.tickets.TicketsViewModel

class SearchBottomSheet:
    BaseBtmSheetFragment<BtmSheetSearchBinding>(
        BtmSheetSearchBinding::inflate
    ){


    private val ticketPageViewModel: TicketsViewModel by activityViewModels<TicketsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        ticketPageViewModel.loadPopularPlaces()

        setupObservers()

        return binding.root
    }

    private fun setupObservers(){
        ticketPageViewModel.popularPlaces.observe(viewLifecycleOwner){ offers ->
            val adapter = PlacesAdapter(offers)  // Replace with your data list
            binding.rcPlaces.adapter = adapter
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        return dialog
    }



}