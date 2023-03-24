package com.example.travlingapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travlingapp.api.YelpService
import com.example.travlingapp.data.LoadingStatus
import com.example.travlingapp.data.YelpRepo
import com.example.travlingapp.data.YelpRepository
import kotlinx.coroutines.launch

class YelpSearchViewModel: ViewModel() {
    private val repository = YelpRepository(YelpService.create())

    private val _searchResults = MutableLiveData<List<YelpRepo>?>(null)
    val searchResults: LiveData<List<YelpRepo>?> = _searchResults

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _loadingStatus = MutableLiveData<LoadingStatus>(LoadingStatus.SUCCESS)
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus


    fun loadSearchResults(query: String, sortBy: String){
        viewModelScope.launch{
            _loadingStatus.value = LoadingStatus.LOADING

            val result = repository.loadRestaurantsSearch(query, sortBy)
            _searchResults.value = result.getOrNull()
            _errorMessage.value = result.exceptionOrNull()?.message
            _loadingStatus.value = when(result.isSuccess){
                true -> LoadingStatus.SUCCESS
                false -> LoadingStatus.ERROR
            }
        }
    }
}