package com.tengtonghann.android.quotieum.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.paging.ExperimentalPagingApi
import com.tengtonghann.android.quotieum.R
import com.tengtonghann.android.quotieum.databinding.ActivityMainBinding
import com.tengtonghann.android.quotieum.ui.favorite.FavoriteFragment
import com.tengtonghann.android.quotieum.ui.quote.QuoteFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalPagingApi
@AndroidEntryPoint
@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivityLog"
    }

    private lateinit var binding: ActivityMainBinding
    private var activeFragment: Fragment? = null
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        setUpNavigationObserver()
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun setUpNavigationObserver() {
        mainViewModel.quoteNavigationLiveData.observe(this) {
            it.getIfNotHandled()?.run { showQuote() }
        }

        mainViewModel.favoriteNavigationLiveData.observe(this) {
            it.getIfNotHandled()?.run { showFavorite() }
        }
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    private fun showQuote() {
        if (activeFragment is QuoteFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(QuoteFragment.TAG) as QuoteFragment?

        if (fragment == null) {
            fragment = QuoteFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, QuoteFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)
        fragmentTransaction.commit()
        activeFragment = fragment
    }

    private fun showFavorite() {
        if (activeFragment is FavoriteFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(FavoriteFragment.TAG) as FavoriteFragment?

        if (fragment == null) {
            fragment = FavoriteFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, FavoriteFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)
        fragmentTransaction.commit()
        activeFragment = fragment
    }

    private fun setUpView() {
        mainViewModel.onCreated()

        binding.bottomNavigationId.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemHome -> {
                    mainViewModel.onQuoteSelected()
                    true
                }
                R.id.itemFavorite -> {
                    mainViewModel.onFavoriteSelected()
                    true
                }
                else -> false
            }
        }
    }
}