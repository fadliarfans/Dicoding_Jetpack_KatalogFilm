package com.example.katalogfilm_byfadli.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
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
import com.example.katalogfilm_byfadli.utils.EspressoIdlingResource
import com.example.katalogfilm_byfadli.utils.changeDateFormat
import com.example.katalogfilm_byfadli.utils.generateGenre
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovies = DataDummy.loadMoviesData()
    private val dummyTvShows = DataDummy.loadTvShowsData()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

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
                1,
                click()
            )
        )
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovies[1].title)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(
            matches(
                withText(
                    dummyMovies[1].genreIds.generateGenre()
                )
            )
        )

        onView(withId(R.id.tv_overview_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_value)).check(matches(withText(dummyMovies[1].overview)))
        onView(withId(R.id.tv_first_air_date_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_first_air_date_value)).check(
            matches(
                withText(
                    dummyMovies[1].releaseDate.changeDateFormat()
                )
            )
        )
        onView(withId(R.id.tv_vote_average_value)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_vote_average_value)).check(matches(withText(dummyMovies[1].voteAverage.toString())))
        onView(withId(R.id.tv_vote_total_value)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_vote_total_value)).check(matches(withText(dummyMovies[1].voteCount.toString())))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size - 2
            )
        )
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                10,
                click()
            )
        )
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyTvShows[10].name)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(
            matches(
                withText(
                    dummyTvShows[10].genreIds.generateGenre()
                )
            )
        )
        onView(withId(R.id.tv_overview_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_value)).check(matches(withText(dummyTvShows[10].overview)))
        onView(withId(R.id.tv_first_air_date_value)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_first_air_date_value)).check(
            matches(
                withText(
                    dummyTvShows[10].firstAirDate.changeDateFormat()
                )
            )
        )
        onView(withId(R.id.tv_vote_average_value)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_vote_average_value)).check(matches(withText(dummyTvShows[10].voteAverage.toString())))
        onView(withId(R.id.tv_vote_total_value)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_vote_total_value)).check(matches(withText(dummyTvShows[10].voteCount.toString())))
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

