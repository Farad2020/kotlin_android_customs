package dev.farad2020.planeticketseller.ui.destination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dev.farad2020.planeticketseller.databinding.FragmentDestinationSelectedBinding
import dev.farad2020.planeticketseller.ui.base.BindingFragment
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
//        binding.searchbar.root.setOnClickListener {
//            showBottomSheet()
//        }

        binding.searchbar.icBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvShowTickets.setOnClickListener {
            val action =
                DestinationSelectedFragmentDirections.actionDestinationSelectedFragmentToTicketListFragment()
            findNavController().navigate(action)
        }

    }



    companion object{
        const val TICKETS_TO_SHOW = 3
    }
}