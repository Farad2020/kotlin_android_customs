package kz.farad2020.repoloader.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kz.farad2020.domain.model.GitHubRepository
import kz.farad2020.domain.usecase.BaseUseCasesPack
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: BaseUseCasesPack
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _downloadResult = MutableLiveData<String>()
    val downloadResult: LiveData<String> = _downloadResult


    private val _list = MutableLiveData<List<GitHubRepository>>().apply {
        value = listOf()
    }
    val list: LiveData<List<GitHubRepository>> = _list


    fun performSearch(query: String){
        if(query.isEmpty())
            return

        viewModelScope.launch {
//            TODO add handler that wraps answer
            _list.value = useCases.searchRepository(query.trim())
        }
    }

    fun downloadRepository(repoData: GitHubRepository) {
        viewModelScope.launch {
            val file = useCases.downloadRepository(repoData)
            file?.let {
                _downloadResult.value = "File downloaded successfully at: ${it.absolutePath}"
            } ?: run {
                _downloadResult.value = "File download failed"
            }
        }
    }

}