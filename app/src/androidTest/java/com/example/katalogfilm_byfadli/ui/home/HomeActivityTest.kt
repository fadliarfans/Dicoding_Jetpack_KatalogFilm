package com.example.katalogfilm_byfadli.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.katalogfilm_byfadli.R
import com.example.katalogfilm_byfadli.utils.DataDummy
import com.example.katalogfilm_byfadli.utils.GlobalFunctions
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovies = DataDummy.loadMoviesData()
    private val dummyTvShows = DataDummy.loadTvShowsData()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(0))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size - 1
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size - 1
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(0))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(
            matches(
                withText(
                    GlobalFunctions.generateGenre(
                        dummyMovies[0].genreIds
                    )
                )
            )
        )
        onView(withId(R.id.tv_overview_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_value)).check(matches(withText(dummyMovies[0].overview)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(
            matches(
                withText(
                    dummyMovies[0].releaseDate?.subSequence(
                        0,
                        4
                    ).toString()
                )
            )
        )
        onView(withId(R.id.tv_vote_average_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_average_value)).check(matches(withText(dummyMovies[0].voteAverage.toString())))
        onView(withId(R.id.tv_vote_total_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_total_value)).check(matches(withText(dummyMovies[0].voteCount.toString())))
        onView(withId(R.id.tv_popularity_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity_value)).check(
            matches(
                withText(
                    dummyMovies[0].popularity?.toInt().toString()
                )
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        Thread.sleep(1000)
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyTvShows[0].name)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(
            matches(
                withText(
                    GlobalFunctions.generateGenre(
                        dummyTvShows[0].genreIds
                    )
                )
            )
        )
        onView(withId(R.id.tv_overview_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_value)).check(matches(withText(dummyTvShows[0].overview)))
        onView(withId(R.id.tv_first_air_date_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_first_air_date_value)).check(
            matches(
                withText(
                    GlobalFunctions.changeDateFormat(dummyTvShows[0].firstAirDate ?: "0000:00:00")
                )
            )
        )
        onView(withId(R.id.tv_vote_average_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_average_value)).check(matches(withText(dummyTvShows[0].voteAverage.toString())))
        onView(withId(R.id.tv_vote_total_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_total_value)).check(matches(withText(dummyTvShows[0].voteCount.toString())))
        onView(withId(R.id.tv_popularity_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity_value)).check(
            matches(
                withText(
                    dummyTvShows[0].popularity?.toInt().toString()
                )
            )
        )
        onView(withId(R.id.tv_origin_country_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_origin_country_value)).check(
            matches(
                withText(
                    dummyTvShows[0].originCountry.toString()
                        .subSequence(1, dummyTvShows[0].originCountry.toString().length - 1)
                        .toString()
                )
            )
        )
    }

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() =
                allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }
}

