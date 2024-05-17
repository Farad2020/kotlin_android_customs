package dev.farad2020.planeticketseller.ui.ticketList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.farad2020.planeticketseller.R
import dev.farad2020.planeticketseller.databinding.FragmentTicketListBinding
import dev.farad2020.planeticketseller.databinding.FragmentTicketsMainBinding
import dev.farad2020.planeticketseller.ui.base.BindingFragment
import dev.farad2020.planeticketseller.ui.base.ItemSpacingDecoration
import dev.farad2020.planeticketseller.ui.btm_sheet.SearchBottomSheet
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class TicketListFragment : BindingFragment<FragmentTicketListBinding>(
    FragmentTicketListBinding::inflate
) {

    private val args by navArgs<TicketListFragmentArgs>()

    private val ticketPageViewModel: TicketListViewModel by sharedViewModel<TicketListViewModel>()

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
        ticketPageViewModel.loadTickets()
    }

    private fun setupObservers(){
        ticketPageViewModel.tickets.observe(viewLifecycleOwner){ tickets ->
            val adapter = TicketsAdapters(tickets)
            binding.rcTickets.adapter = adapter
        }
    }

    private fun setupView(){
        setupTitleLayout()

    }

    private fun setupTitleLayout(){
        binding.tvTitle.text = getString(R.string.title_all_tickets_places,
            args.pageData.departureCity,
            args.pageData.arrivalCity)


        binding.tvSubtitle.text = getString(R.string.title_all_tickets_info,
            args.pageData.flightDate,
            args.pageData.flightInfo)

        binding.layoutTitle.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}