package com.github.fajaragungpramana.igit.module.search

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.common.contract.AppState
import com.github.fajaragungpramana.igit.databinding.SearchFragmentBinding
import com.github.fajaragungpramana.igit.databinding.ShimmerItemUserBinding
import com.github.fajaragungpramana.igit.widget.extension.setMargins
import com.github.fajaragungpramana.igit.module.adapter.LoadStateAdapter
import com.github.fajaragungpramana.igit.module.adapter.UserAdapter
import com.github.fajaragungpramana.igit.module.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : AppFragment<SearchFragmentBinding>(), AppState {

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var userAdapter: UserAdapter

    override fun onViewBinding(container: ViewGroup?): SearchFragmentBinding =
        SearchFragmentBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initView()
        initUser()
        initSearch()
        initUserLoadState()

        viewModel.setEvent(SearchEvent.SearchUser(username = "a"))
    }

    override fun onStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {
                when (it) {
                    is SearchState.UserData -> userAdapter.submitData(it.pagingData)
                }
            }
        }
    }

    private fun initView() {
        (requireActivity() as MainActivity).apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun initUserLoadState() {
        userAdapter.addLoadStateListener {
            val isLoading = it.refresh is LoadState.Loading
            viewBinding.apply {
                sflShimmerItemUser.isVisible = isLoading
                rvUser.isVisible = !isLoading

                if (isLoading) {
                    llShimmerItemUser.removeAllViews()
                    sflShimmerItemUser.startShimmer()

                    for (i in 1..5) {
                        val shimmerItemUser = ShimmerItemUserBinding.inflate(layoutInflater).root
                        llShimmerItemUser.addView(shimmerItemUser)

                        shimmerItemUser.setMargins(16f, 16f, 16f, 16f)
                    }
                } else
                    sflShimmerItemUser.stopShimmer()
            }
        }
    }

    private fun initSearch() {
        viewBinding.tieSearchUsername.addTextChangedListener {
            viewModel.setEvent(SearchEvent.SearchUser(it.toString()))
        }
    }

    private fun initUser() {
        userAdapter = UserAdapter {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it.username)
            findNavController().navigate(action)
        }

        viewBinding.apply {
            rvUser.layoutManager = LinearLayoutManager(requireActivity())
            rvUser.adapter = userAdapter.withLoadStateFooter(LoadStateAdapter())
        }
    }

}
