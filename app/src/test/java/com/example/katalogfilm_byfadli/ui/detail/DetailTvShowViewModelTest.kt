package com.example.katalogfilm_byfadli.ui.detail

import com.example.katalogfilm_byfadli.utils.GlobalFunctions
import org.junit.Before
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.lang.NullPointerException

class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getDetailTvShow() {
        val detailTvShow = viewModel.getDetailTvShow(77169)
        assertThat(detailTvShow).isNotNull()
        // id test
        assertThat(detailTvShow?.id).isEqualTo(77169)
        // name test
        assertThat(detailTvShow?.title).isEqualTo("Cobra Kai")
        // genre test
        assertThat(
            GlobalFunctions.generateGenre(detailTvShow?.genreIds)
        ).isEqualTo("Action & Adventure, Drama")
        assertThat(
            detailTvShow?.genreIds
        ).isEqualTo(
            listOf(
                10759,
                18
            )
        )
        // origin country test
        assertThat(
            detailTvShow?.originCountry
        ).isEqualTo(
            listOf(
                "US"
            )
        )
        // original language test
        assertThat(detailTvShow?.originalLanguage).isEqualTo("en")
        // original name test
        assertThat(detailTvShow?.originalName).isEqualTo("Cobra Kai")
        // overview test
        assertThat(

            detailTvShow?.overview
        ).isEqualTo("This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.")
        // release date test
        assertThat(detailTvShow?.firstAirDate).isEqualTo("2018-05-02")
        assertThat(
            viewModel.changeDateFormat(
                detailTvShow?.firstAirDate ?: "0000-00-00"
            )
        ).isEqualTo("02 Mei 2018")
        // poster path test
        assertThat(detailTvShow?.posterPath).isEqualTo("/yR9a9yZ1kAW0Xbl4NxQxPSMteEg.jpg")
        // backdrop path test
        assertThat(detailTvShow?.backdropPath).isEqualTo("/g63KmFgqkvXu6WKS23V56hqEidh.jpg")
        // vote average test
        assertThat(detailTvShow?.voteAverage).isEqualTo(8.1)
        // vote count test
        assertThat(detailTvShow?.voteCount).isEqualTo(3623)
        // popularity test
        assertThat(detailTvShow?.popularity).isEqualTo(659.888)
        assertThat(detailTvShow?.popularity?.toInt()).isEqualTo(659)
    }

    @Test(expected = NullPointerException::class)
    fun detailTvShowFailedToGet() {
        val tvShow = viewModel.getDetailTvShow(123456)
        assertThat(tvShow).isNull()
        tvShow!!.title
    }
}