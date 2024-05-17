package dev.farad2020.planeticketseller.ui.destination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.farad2020.planeticketseller.databinding.FragmentDestinationSelectedBinding
import dev.farad2020.planeticketseller.ui.base.BindingFragment
import dev.farad2020.planeticketseller.ui.base.getCurrentDateInRussianFormat
import dev.farad2020.planeticketseller.ui.base.gone
import dev.farad2020.planeticketseller.ui.base.visible
import dev.farad2020.planeticketseller.ui.navModel.TicketListNavPayload
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DestinationSelectedFragment : BindingFragment<FragmentDestinationSelectedBinding>(
    FragmentDestinationSelectedBinding::inflate
) {

    private val args by navArgs<DestinationSelectedFragmentArgs>()
    private val ticketPageViewModel: DestinationSelectedViewModel by sharedViewModel<DestinationSelectedViewModel>()

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
            val adapter = TicketOffersAdapter(ticketOffers)
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
            openTickets()
        }

        binding.snippetFlights.tvShowAll.setOnClickListener {
//            openTickets()
        }

    }

    private fun openTickets(){
        val pageData = TicketListNavPayload(
            departureCity = binding.searchbar.searchTextFirst.text.toString(),
            arrivalCity = binding.searchbar.searchTextSecond.text.toString(),
            flightDate = binding.chipGroup.tvDate.text.toString(),
            flightInfo = binding.chipGroup.tvFlightInfo.text.toString()
        )

        val action =
            DestinationSelectedFragmentDirections.actionDestinationSelectedFragmentToTicketListFragment(pageData)
        findNavController().navigate(action)
    }

    private fun setupSearchBar(){
        binding.searchbar.icSwap.setOnClickListener {
            swapSearchbarInputs()
        }

        binding.searchbar.icBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.searchbar.searchTextFirst.text = args.pageData.departureCity
        binding.searchbar.searchTextSecond.text = args.pageData.arrivalCity
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