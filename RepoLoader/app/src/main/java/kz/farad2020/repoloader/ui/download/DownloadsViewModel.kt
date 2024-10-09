package kz.farad2020.repoloader.ui.download

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DownloadsViewModel @Inject constructor() : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    fun setMessage(text: String){
        _text.value = text.trim()
    }
}