package com.example.katalogfilm_byfadli.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
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