package com.github.fajaragungpramana.igit.module.search

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.common.contract.AppState
import com.github.fajaragungpramana.igit.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : AppFragment<SearchFragmentBinding>(), AppState {

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewBinding(container: ViewGroup?): SearchFragmentBinding =
        SearchFragmentBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        viewModel.setEvent(SearchEvent.SearchUser("fajaragungpramana"))
    }

    override fun onStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {
                when (it) {
                    is SearchState.UserData -> Log.d("FFFF", "${it.pagingData}")
                }
            }
        }
    }

}