package kz.farad2020.repoloader.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kz.farad2020.domain.model.GitHubRepository
import kz.farad2020.repoloader.databinding.FragmentSearchBinding
import kz.farad2020.repoloader.ui.base.BindingFragment
import kz.farad2020.repoloader.ui.base.UiState
import kz.farad2020.repoloader.ui.base.gone
import kz.farad2020.repoloader.ui.base.hideKeyboard
import kz.farad2020.repoloader.ui.base.showSnackbar
import kz.farad2020.repoloader.ui.base.visible
import kz.farad2020.repoloader.ui.search.adapters.RepositoriesAdapter

@AndroidEntryPoint
class SearchFragment : BindingFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
), RepositoriesAdapter.OnClickRepo {

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        setupObservers()

        setupEtSearch()

        return binding.root
    }


    //   TODO add loader ic, close keyboard
    private fun setupEtSearch(){
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.etSearch.text.toString().trim()
                viewModel.performSearch(query)
                hideKeyboard()

                // Event consumed
                true
            } else {
                // Event wasn't handled
                false
            }
        }
    }

    private fun setupObservers(){
        setupDownloadResultListener()

        setupRcRepositoriesObserver()
    }

    private fun setupDownloadResultListener(){
        viewModel.downloadResult.observe(viewLifecycleOwner){ downloadResult ->
            if(downloadResult.isNotEmpty()){
                showSnackbar(binding.root, downloadResult)
                viewModel.resetDownloadResultData()
            }
        }

    }

//   TODO add loader ic, close keyboard
    private fun setupRcRepositoriesObserver(){
        viewModel.searchUiState.observe(viewLifecycleOwner){ state ->
            when (state) {
                is UiState.Loading -> onRepositoriesLoading()
                is UiState.Success -> onRepositoryLoadSuccess(state.data)
                is UiState.Error -> onRepositoryLoadError(state.message)
            }
        }
    }

    private fun onRepositoriesLoading(){
        binding.pbCircle.visible()
        binding.tvMessage.gone()
        binding.rcRepositories.gone()
    }

    private fun onRepositoryLoadError(message: String){
        binding.pbCircle.gone()
        binding.tvMessage.gone()
        binding.rcRepositories.gone()

        showSnackbar(binding.root, message)
    }

    private fun onRepositoryLoadSuccess(data: List<GitHubRepository>){
        binding.pbCircle.gone()

        if(data.isEmpty()){
            binding.tvMessage.visible()
            binding.rcRepositories.gone()
            return
        }

        binding.tvMessage.gone()
        binding.rcRepositories.visible()

        setAdapterData(data)
    }

    private fun setAdapterData(repositories: List<GitHubRepository>){
        if(binding.rcRepositories.adapter != null && binding.rcRepositories.adapter is RepositoriesAdapter){
            (binding.rcRepositories.adapter as RepositoriesAdapter).replaceRepositories(repositories)
        }else{
            val adapter = RepositoriesAdapter(repositories.toMutableList(), this)
            binding.rcRepositories.adapter = adapter
        }
    }

    override fun onDownloadRepository(data: GitHubRepository, onDownloadFinish: () -> Unit) {
        viewModel.downloadRepository(data, onDownloadFinish)
    }

    /**
     * I could use here webview, but for now not necessary
     */
    override fun onOpenBrowser(data: GitHubRepository) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(data.htmlUrl)
        startActivity(intent)
    }
}