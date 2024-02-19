package com.github.fajaragungpramana.igit.module.search

import android.os.Bundle
import android.view.ViewGroup
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.databinding.SearchFragmentBinding

class SearchFragment : AppFragment<SearchFragmentBinding>() {

    override fun onViewBinding(container: ViewGroup?): SearchFragmentBinding =
        SearchFragmentBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(savedInstanceState: Bundle?) {

    }

}