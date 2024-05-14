package dev.farad2020.planeticketseller.ui.ticketList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dev.farad2020.planeticketseller.databinding.FragmentTicketListBinding
import dev.farad2020.planeticketseller.databinding.FragmentTicketsMainBinding
import dev.farad2020.planeticketseller.ui.base.BindingFragment
import dev.farad2020.planeticketseller.ui.base.ItemSpacingDecoration
import dev.farad2020.planeticketseller.ui.btm_sheet.SearchBottomSheet


class TicketListFragment : BindingFragment<FragmentTicketListBinding>(
    FragmentTicketListBinding::inflate
) {

    private val ticketPageViewModel: TicketListViewModel by activityViewModels<TicketListViewModel>()

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
        ticketPageViewModel.loadOffers()
    }

    private fun setupObservers(){
        ticketPageViewModel.offers.observe(viewLifecycleOwner){ offers ->
            val adapter = TicketsAdapters(offers)  // Replace with your data list
            binding.rcTickets.adapter = adapter
        }
    }

    private fun setupView(){
//        binding.searchbar.root.setOnClickListener {
//            showBottomSheet()
//        }

        binding.layoutTitle.setOnClickListener {
            findNavController().navigateUp()
        }

    }
}