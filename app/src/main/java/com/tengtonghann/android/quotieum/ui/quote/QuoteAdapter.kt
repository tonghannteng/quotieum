package com.tengtonghann.android.quotieum.ui.quote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.tengtonghann.android.quotieum.data.Data
import com.tengtonghann.android.quotieum.databinding.ItemQuoteBinding

class QuoteAdapter : PagingDataAdapter<Data, QuotesViewHolder>(QUOTE_COMPARATOR) {

    //to compare the data using diffUtil
    companion object {
        private val QUOTE_COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val binding = ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuotesViewHolder(binding)
    }
}