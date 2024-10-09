package kz.farad2020.repoloader.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kz.farad2020.domain.model.GitHubRepository
import kz.farad2020.domain.model.NetworkResult
import kz.farad2020.domain.usecase.BaseUseCasesPack
import kz.farad2020.repoloader.ui.base.UiState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: BaseUseCasesPack
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _downloadResult = MutableLiveData<String>()
    val downloadResult: LiveData<String> = _downloadResult


    private var fetchRepositoriesJob: Job? = null

    private val _searchUiState = MutableLiveData<UiState<List<GitHubRepository>>>()
    val searchUiState: LiveData<UiState<List<GitHubRepository>>> = _searchUiState

    fun performSearch(query: String){
        if(query.isEmpty())
            return

        fetchRepositoriesJob?.cancel()

        fetchRepositoriesJob = viewModelScope.launch {
             useCases.searchRepository(query.trim()).collect { result ->
                 processSearchResult(result)
            }
        }
    }

    private fun processSearchResult(result: NetworkResult<List<GitHubRepository>>){
        when (result) {
            is NetworkResult.Loading -> {
                _searchUiState.postValue(UiState.Loading)
            }
            is NetworkResult.Success -> {
                result.data?.let { data ->
                    _searchUiState.postValue(UiState.Success(data))
                }
            }
            is NetworkResult.Error -> {
                _searchUiState.postValue(UiState.Error(result.message ?: "Some error occurred"))
            }
            is NetworkResult.None -> {}
        }
    }

    fun resetDownloadResultData(){
        _downloadResult.value = ""
    }

    fun downloadRepository(repoData: GitHubRepository, onDownloadFinish: () -> Unit) {
        viewModelScope.launch {
            val file = useCases.downloadRepository(repoData)
            onDownloadFinish()
            file?.let {
                _downloadResult.value = "File downloaded successfully"
            } ?: run {
                _downloadResult.value = "File download failed"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        fetchRepositoriesJob?.cancel()
    }

}