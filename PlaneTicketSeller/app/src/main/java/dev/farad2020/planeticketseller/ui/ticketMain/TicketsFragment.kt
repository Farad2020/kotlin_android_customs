package dev.farad2020.planeticketseller.ui.ticketMain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dev.farad2020.planeticketseller.databinding.FragmentTicketsMainBinding
import dev.farad2020.planeticketseller.ui.base.BindingFragment
import dev.farad2020.planeticketseller.ui.base.ItemSpacingDecoration
import dev.farad2020.planeticketseller.ui.btm_sheet.SearchBottomSheet


//TODO check if I can refactor Vectors
class TicketsFragment : BindingFragment<FragmentTicketsMainBinding>(
    FragmentTicketsMainBinding::inflate
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
            val adapter = OffersAdapter(offers)
            binding.rcOffers.adapter = adapter
        }
    }

    private fun setupView(){
        binding.searchbar.root.setOnClickListener {
            showBottomSheet()
        }


        val spacingDecoration = ItemSpacingDecoration(
            dpToPx(GENERAL_LI_SPACING),
            dpToPx(LAST_LI_SPACING),
            )
        binding.rcOffers.addItemDecoration(spacingDecoration)

    }


    private fun showBottomSheet(){
        if (searchBtmSheet.isAdded)
            return

        searchBtmSheet.show(childFragmentManager, SearchBottomSheet::class.simpleName)
    }

    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }

    companion object{
        private const val GENERAL_LI_SPACING = 67
        private const val LAST_LI_SPACING = 16
    }
}