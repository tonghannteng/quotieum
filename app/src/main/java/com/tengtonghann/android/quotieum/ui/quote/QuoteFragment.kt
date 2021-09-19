package com.tengtonghann.android.quotieum.ui.quote

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.tengtonghann.android.quotieum.R
import com.tengtonghann.android.quotieum.databinding.FragmentQuoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalPagingApi
@InternalCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class QuoteFragment : Fragment(R.layout.fragment_quote) {

    private lateinit var binding: FragmentQuoteBinding
    private val quoteViewModel: QuoteViewModel by viewModels()
    private val quoteAdapter = QuoteAdapter(
        onFavoriteQuote = {
            quoteViewModel.insertFavoriteQuote(data = it)
        }
    )

    companion object {
        const val TAG = "QuoteFragment"

        fun newInstance(): QuoteFragment {
            val args = Bundle()
            val fragment = QuoteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuoteBinding.bind(view)
        binding.quoteRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = quoteAdapter
        }

        quoteViewModel.getQuotesList()

        quoteViewModel.quoteList.observe(
            viewLifecycleOwner, { quoteAdapter.submitData(lifecycle, it) }
        )
    }

}