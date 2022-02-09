package com.example.katalogfilm_byfadli.ui.favorite

import androidx.lifecycle.*

class FavoriteViewModel : ViewModel() {
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