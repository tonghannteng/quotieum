package com.tengtonghann.android.quotieum.ui.favorite

import androidx.recyclerview.widget.RecyclerView
import com.tengtonghann.android.quotieum.data.Data
import com.tengtonghann.android.quotieum.databinding.ItemQuoteBinding

class FavoriteViewHolder(
    private val binding: ItemQuoteBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Data) {
        binding.apply {
            quoteText.text = data.quoteText
        }
    }
}