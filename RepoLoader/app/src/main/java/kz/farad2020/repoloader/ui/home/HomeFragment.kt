package kz.farad2020.repoloader.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kz.farad2020.repoloader.databinding.FragmentHomeBinding
import kz.farad2020.repoloader.ui.base.BindingFragment
import kz.farad2020.repoloader.ui.home.adapters.RepositoriesAdapter

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        setupViews()

        return binding.root
    }

    private fun setupViews(){
        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        setupRcRepositories()
    }

    private fun setupRcRepositories(){
        val data = listOf(
            Pair("Title 1", "This is the subtitle 1"),
            Pair("Title 2", "This is the subtitle 2"),
            Pair("Title 3", "This is the subtitle 3"),
            Pair("Title 4", "This is the subtitle 4")
        )

        val adapter = RepositoriesAdapter(data)
        binding.rcRepositories.adapter = adapter
    }
}