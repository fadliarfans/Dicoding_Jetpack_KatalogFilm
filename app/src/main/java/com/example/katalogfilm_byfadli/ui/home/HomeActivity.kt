package com.example.katalogfilm_byfadli.ui.home

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.katalogfilm_byfadli.R
import com.example.katalogfilm_byfadli.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initAppbar()
        initViewPagerAdapter()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]
    }

    private fun initViewPagerAdapter() {
        binding.viewPager.adapter = SectionsPagerAdapter(this)
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = (this.resources.getString(TAB_TITLES[position]))
        }.attach()
    }

    private fun initAppbar() {
        supportActionBar?.elevation = 0f
        title = "Katalog Film"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.setSearchData(query)
                try {
                    val imm: InputMethodManager =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
                } catch (e: Throwable) {
                    Log.v("DEBUG", e.toString())
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.setSearchData(newText)
                return false
            }

        })
        return true
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_shows)
    }
}