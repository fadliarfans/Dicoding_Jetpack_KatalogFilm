package com.example.katalogfilm_byfadli.ui.detail

import androidx.lifecycle.ViewModel
import com.example.katalogfilm_byfadli.utils.DataDummy
import com.example.katalogfilm_byfadli.utils.GlobalFunctions

class DetailViewModel : ViewModel() {
    fun getDetailTvShow(tvShowId: Int) {
    }

    fun generateGenre(listOfIdGenres: List<Int?>?): String =
        GlobalFunctions.generateGenre(listOfIdGenres)

    fun changeDateFormat(date: String): String = GlobalFunctions.changeDateFormat(date)
}