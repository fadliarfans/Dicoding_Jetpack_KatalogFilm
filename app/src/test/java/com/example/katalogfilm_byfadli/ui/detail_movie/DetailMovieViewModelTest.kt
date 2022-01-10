package com.example.katalogfilm_byfadli.ui.detail_movie

import com.example.katalogfilm_byfadli.utils.GlobalFunctions
import org.junit.Before
import org.junit.Test
import java.lang.NullPointerException
import com.google.common.truth.Truth.assertThat


class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
    }

    @Test
    fun getDetailMovie() {
        val detailMovie = viewModel.getDetailMovie(728526)
        assertThat(detailMovie).isNotNull()
        // id test
        assertThat(detailMovie?.id).isEqualTo(728526)
        // title test
        assertThat(detailMovie?.title).isEqualTo("Encounter")
        // genre test
        assertThat(
            GlobalFunctions.generateGenre(detailMovie?.genreIds)
        ).isEqualTo("Science Fiction, Thriller, Adventure")
        assertThat(
            detailMovie?.genreIds
        ).isEqualTo(
            listOf(
                878,
                53,
                12
            )
        )
        // adult test
        assertThat(detailMovie?.adult).isEqualTo(false)
        // original language test
        assertThat(detailMovie?.originalLanguage).isEqualTo("en")
        // original title test
        assertThat(detailMovie?.originalTitle).isEqualTo("Encounter")
        // overview test
        assertThat(
            detailMovie?.overview
        ).isEqualTo("A decorated Marine goes on a rescue mission to save his two young sons from an unhuman threat. As their journey takes them in increasingly dangerous directions, the boys will need to leave their childhoods behind.")
        // release date test
        assertThat(detailMovie?.releaseDate).isEqualTo("2021-12-03")
        assertThat(detailMovie?.releaseDate?.subSequence(0, 4).toString()).isEqualTo("2021")
        // poster path test
        assertThat( detailMovie?.posterPath).isEqualTo("/tUkY0WxffPZ9PoyC62PIyyUMGnt.jpg",)
        // backdrop path test
        assertThat( detailMovie?.backdropPath).isEqualTo("/zlj0zHo67xXoj7hvwGtaKRkSdBV.jpg")
        // vote average test
        assertThat( detailMovie?.voteAverage).isEqualTo(6.3)
        // vote count test
        assertThat( detailMovie?.voteCount).isEqualTo(134)
        // popularity test
        assertThat( detailMovie?.popularity).isEqualTo(2263.981)
        assertThat(detailMovie?.popularity?.toInt()).isEqualTo(2263)
    }

    @Test(expected = NullPointerException::class)
    fun detailMovieFailedToGet() {
        val movie = viewModel.getDetailMovie(123456)
        assertThat(movie).isNull()
        movie!!.title
    }
}