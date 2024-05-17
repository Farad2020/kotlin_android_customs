package dev.farad2020.planeticketseller.ui.destination

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import dev.farad2020.planeticketseller.R
import dev.farad2020.planeticketseller.databinding.FragmentDestinationSelectedBinding
import dev.farad2020.planeticketseller.ui.base.BindingFragment
import dev.farad2020.planeticketseller.ui.base.getCurrentDateInRussianFormat
import dev.farad2020.planeticketseller.ui.base.gone
import dev.farad2020.planeticketseller.ui.base.visible


class DestinationSelectedFragment : BindingFragment<FragmentDestinationSelectedBinding>(
    FragmentDestinationSelectedBinding::inflate
) {

    private val ticketPageViewModel: DestinationSelectedViewModel by activityViewModels<DestinationSelectedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        setupViewModel()

        setupView()

        setupObservers()

        return binding.root
    }

    private fun setupViewModel(){
        ticketPageViewModel.loadTickets(TICKETS_TO_SHOW)
    }

    private fun setupObservers(){
        ticketPageViewModel.ticketOffers.observe(viewLifecycleOwner){ ticketOffers ->
            val adapter = TicketOffersAdapter(ticketOffers, TICKETS_TO_SHOW)
            binding.snippetFlights.rcPlaces.adapter = adapter

            if(ticketOffers.size > TICKETS_TO_SHOW){
                binding.snippetFlights.tvShowAll.gone(true)
            }else{
                binding.snippetFlights.tvShowAll.visible(true)
            }
        }
    }

    private fun setupView(){
        setupSearchBar()

        setupActionButtons()

        binding.tvShowTickets.setOnClickListener {
            val action =
                DestinationSelectedFragmentDirections.actionDestinationSelectedFragmentToTicketListFragment()
            findNavController().navigate(action)
        }

    }

    private fun setupSearchBar(){
        binding.searchbar.icSwap.setOnClickListener {
            swapSearchbarInputs()
        }

        binding.searchbar.icBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupActionButtons(){
        binding.chipGroup.apply {
            chipBack.setOnClickListener {
//                showDatePicker()
            }


            tvDate.text = getCurrentDateInRussianFormat()

            chipDate.setOnClickListener {

            }

            chipType.setOnClickListener {
//              Do nothing
            }

            chipFilters.setOnClickListener {
//              Do nothing
            }
        }
    }

    private fun swapSearchbarInputs(){
        val textFirst = binding.searchbar.searchTextFirst.text
        binding.searchbar.searchTextFirst.text = binding.searchbar.searchTextSecond.text
        binding.searchbar.searchTextSecond.text = textFirst
    }



    companion object{
        const val TICKETS_TO_SHOW = 3
    }
}