package com.example.katalogfilm_byfadli.ui.detail_tvshow

import androidx.lifecycle.ViewModel
import com.example.katalogfilm_byfadli.data.TvShowEntity
import com.example.katalogfilm_byfadli.utils.DataDummy
import com.example.katalogfilm_byfadli.utils.GlobalFunctions

class DetailTvShowViewModel : ViewModel() {
    fun getDetailTvShow(tvShowId: Int): TvShowEntity? {
        val listTvShows = DataDummy.loadTvShowsData()
        var tvShow: TvShowEntity? = null
        for (it in listTvShows) {
            if (it.id == tvShowId) {
                tvShow = it
                break
            }
        }
        return tvShow
    }

    fun generateGenre(listOfIdGenres: List<Int?>?): String =
        GlobalFunctions.generateGenre(listOfIdGenres)

    fun changeDateFormat(date: String): String = GlobalFunctions.changeDateFormat(date)
}