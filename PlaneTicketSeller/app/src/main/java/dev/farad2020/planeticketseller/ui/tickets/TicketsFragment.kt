package dev.farad2020.planeticketseller.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import dev.farad2020.planeticketseller.databinding.FragmentHomeBinding
import dev.farad2020.planeticketseller.databinding.FragmentTicketsBinding
import dev.farad2020.planeticketseller.ui.base.BindingFragment
import dev.farad2020.planeticketseller.ui.btm_sheet.SearchBottomSheet
import dev.farad2020.planeticketseller.ui.home.HomeViewModel


//TODO check if I can refactor Vectors
class TicketsFragment : BindingFragment<FragmentTicketsBinding>(
    FragmentTicketsBinding::inflate
) {

    private val ticketPageViewModel: TicketsViewModel by activityViewModels<TicketsViewModel>()
    private val searchBtmSheet = SearchBottomSheet()

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
            val adapter = OffersAdapter(offers)  // Replace with your data list
            binding.rcOffers.adapter = adapter
        }
    }

    private fun setupView(){
        binding.searchbar.root.setOnClickListener {
            showBottomSheet()
        }

//        binding.rcOffers.layoutManager = LinearLayoutManager(this)

// Optional: Add item decoration for spacing (replace with actual implementation)
//        val itemDecoration = RecyclerView.ItemDecoration { outRect, _, parent, _ ->
//            outRect.left = 67
//            outRect.right = 67
//        }
//        recyclerView.addItemDecoration(itemDecoration)

    }


    private fun showBottomSheet(){
        if (searchBtmSheet.isAdded)
            return

        searchBtmSheet.show(childFragmentManager, SearchBottomSheet::class.simpleName)
    }
}