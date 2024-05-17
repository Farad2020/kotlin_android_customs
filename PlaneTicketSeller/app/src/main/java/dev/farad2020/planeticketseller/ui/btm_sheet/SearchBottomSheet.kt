package dev.farad2020.planeticketseller.ui.btm_sheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dev.farad2020.planeticketseller.R
import dev.farad2020.planeticketseller.databinding.BtmSheetSearchBinding
import dev.farad2020.planeticketseller.ui.navModel.DestinationFragmentNavPayload
import dev.farad2020.planeticketseller.ui.ticketMain.TicketsViewModel

class SearchBottomSheet:
    BaseBtmSheetFragment<BtmSheetSearchBinding>(
        BtmSheetSearchBinding::inflate
    ){
    private val ticketPageViewModel: TicketsViewModel by activityViewModels<TicketsViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState)
        return bottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        ticketPageViewModel.loadPopularPlaces()

        setupObservers()

        setupView()

        return binding.root
    }

    private fun setupObservers(){
        ticketPageViewModel.popularPlaces.observe(viewLifecycleOwner){ offers ->
            val adapter = PlacesAdapter(offers, this::onPlaceClicked)
            binding.rcPlaces.adapter = adapter
        }
    }

    private fun onPlaceClicked(text: String) {
        openDestinationFragment(
            binding.searchLayout.searchTextFirst.text.toString(),
            text
        )
    }

    private fun setupView(){
        binding.searchLayout.searchTextSecond.text = getString(R.string.searchbar_where_to)

        binding.searchLayout.icClear.setOnClickListener {
            binding.searchLayout.searchTextSecond.text = getString(R.string.searchbar_where_to)
        }

        setupLayoutActions()
    }

    private fun openDestinationFragment(depCity: String, arrivalCity: String){
        binding.searchLayout.searchTextSecond.text = arrivalCity

        val pageData = DestinationFragmentNavPayload(
            arrivalCity,
            depCity
        )

        val bundle = Bundle().apply {
            putParcelable("pageData", pageData)
        }

        findNavController().navigate(R.id.destinationSelectedFragment, bundle)
    }

    private fun setupLayoutActions(){
        binding.layoutActions.btnAnywhere.setOnClickListener {
            openDestinationFragment(
                binding.searchLayout.searchTextFirst.text.toString(),
                getString(R.string.label_anywhere))
        }

        binding.layoutActions.btnPath.setOnClickListener {
            openTempPage()
        }
        binding.layoutActions.btnWeekends.setOnClickListener {
            openTempPage()
        }
        binding.layoutActions.btnHotTickets.setOnClickListener {
            openTempPage()
        }
    }

    private fun openTempPage(){
        findNavController().navigate(R.id.tempFragment)
    }
}