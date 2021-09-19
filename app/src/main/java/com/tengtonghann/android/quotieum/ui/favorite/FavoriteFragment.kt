package com.tengtonghann.android.quotieum.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tengtonghann.android.quotieum.R
import com.tengtonghann.android.quotieum.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FavoriteFragment: Fragment(R.layout.fragment_favorite) {

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val favoriteAdapter = FavoriteAdapter()

    companion object {
        const val TAG = "FavoriteFragment"

        fun newInstance(): FavoriteFragment {
            val args = Bundle()
            val fragment = FavoriteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteBinding.bind(view)

        binding.favoriteRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteAdapter
        }

        favoriteViewModel.getFavoriteQuote()
        favoriteViewModel.favoriteQuote.observe(
            viewLifecycleOwner, {
                favoriteAdapter.differ.submitList(it)
            }
        )
    }

}