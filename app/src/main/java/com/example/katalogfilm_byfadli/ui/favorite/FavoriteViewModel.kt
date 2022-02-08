package com.example.katalogfilm_byfadli.ui.favorite

import androidx.lifecycle.*
import com.example.katalogfilm_byfadli.data.source.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class FavoriteViewModel: ViewModel() {
    private val searchData: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }

    fun setSearchData(query: String) {
        searchData.postValue(query)
    }

    fun getSearchData(): LiveData<String?> {
        return searchData
    }
}