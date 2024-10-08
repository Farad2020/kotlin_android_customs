package kz.farad2020.repoloader.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kz.farad2020.domain.model.GitHubRepository
import kz.farad2020.repoloader.databinding.FragmentHomeBinding
import kz.farad2020.repoloader.ui.base.BindingFragment
import kz.farad2020.repoloader.ui.home.adapters.RepositoriesAdapter

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
), RepositoriesAdapter.OnClickRepo {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        setupObservers()

        setupViews()

        return binding.root
    }

    private fun setupObservers(){
        viewModel.downloadResult.observe(viewLifecycleOwner){ downloadResult ->
            Toast.makeText(requireContext(), downloadResult, Toast.LENGTH_SHORT).show()
        }

        setupRcRepositoriesObserver()
    }

    private fun setupViews(){
        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

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
    private fun setupRcRepositoriesObserver(){
        viewModel.list.observe(viewLifecycleOwner){ repositories ->
            if(binding.rcRepositories.adapter != null && binding.rcRepositories.adapter is RepositoriesAdapter){
                (binding.rcRepositories.adapter as RepositoriesAdapter).replaceRepositories(repositories)
            }else{
                val adapter = RepositoriesAdapter(repositories.toMutableList(), this)
                binding.rcRepositories.adapter = adapter
            }
        }
    }

    override fun onDownloadRepository(data: GitHubRepository) {
        viewModel.downloadRepository(data)
    }

    override fun onOpenWvForRepository(data: GitHubRepository) {
        Toast.makeText(requireContext(), "add WebView", Toast.LENGTH_SHORT).show()
    }
}