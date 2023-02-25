package com.saad.homeflix.views.modules.movies

import com.saad.homeflix.data.models.ResultsItem

interface ItemClickListener {
    fun onItemClicked(item: ResultsItem)
}