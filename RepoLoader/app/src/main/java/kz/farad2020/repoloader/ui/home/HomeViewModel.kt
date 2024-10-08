package kz.farad2020.repoloader.ui.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    fun downloadRepository(context: Context, repoData: GitHubRepository) {
        viewModelScope.launch {
            val file = useCases.downloadRepository(repoData.getDownloadLink(), context)
            file?.let {
                // Do something with the file (e.g., notify user that download is complete)
                Toast.makeText(context,
                    "File downloaded successfully at: ${it.absolutePath}",
                    Toast.LENGTH_SHORT).show()
            } ?: run {
                Toast.makeText(context, "File download failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}