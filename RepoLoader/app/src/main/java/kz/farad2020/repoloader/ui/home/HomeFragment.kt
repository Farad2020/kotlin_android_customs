package kz.farad2020.repoloader.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
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

        setupEtSearch()
    }

//   TODO add loader ic, close keyboard
    private fun setupEtSearch(){
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.etSearch.text.toString().trim()
                viewModel.performSearch(query)

                // Event consumed
                true
            } else {
                // Event wasn't handled
                false
            }
        }
    }

//   TODO add loader ic, close keyboard
    private fun setupRcRepositories(){
        viewModel.list.observe(viewLifecycleOwner){ repositories ->
            if(binding.rcRepositories.adapter != null && binding.rcRepositories.adapter is RepositoriesAdapter){
                (binding.rcRepositories.adapter as RepositoriesAdapter).replaceRepositories(repositories)
            }else{
                val adapter = RepositoriesAdapter(repositories.toMutableList())
                binding.rcRepositories.adapter = adapter
            }
        }
    }
}