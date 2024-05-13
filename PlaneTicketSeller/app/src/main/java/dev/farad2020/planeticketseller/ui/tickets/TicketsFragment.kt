package dev.farad2020.planeticketseller.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.farad2020.planeticketseller.databinding.FragmentHomeBinding
import dev.farad2020.planeticketseller.databinding.FragmentTicketsBinding
import dev.farad2020.planeticketseller.ui.base.BindingFragment
import dev.farad2020.planeticketseller.ui.btm_sheet.SearchBottomSheet


//TODO check if I can refactor Vectors
class TicketsFragment : BindingFragment<FragmentTicketsBinding>(
    FragmentTicketsBinding::inflate
) {

    private val searchBtmSheet = SearchBottomSheet()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        setupView()

        return binding.root
    }

    private fun setupView(){
        binding.searchbar.root.setOnClickListener {
            showBottomSheet()
        }
    }


    private fun showBottomSheet(){
        if (searchBtmSheet.isAdded)
            return

        searchBtmSheet.show(childFragmentManager, SearchBottomSheet::class.simpleName)
    }
}