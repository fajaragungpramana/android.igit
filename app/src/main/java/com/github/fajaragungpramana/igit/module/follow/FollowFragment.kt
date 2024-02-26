package com.github.fajaragungpramana.igit.module.follow

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.common.contract.AppState
import com.github.fajaragungpramana.igit.core.data.remote.user.UserPagingSource
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.databinding.FragmentPopularityBinding
import com.github.fajaragungpramana.igit.databinding.ShimmerItemUserBinding
import com.github.fajaragungpramana.igit.module.adapter.LoadStateAdapter
import com.github.fajaragungpramana.igit.module.adapter.UserAdapter
import com.github.fajaragungpramana.igit.widget.extension.setMargins
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FollowFragment(private val login: String) : AppFragment<FragmentPopularityBinding>(), AppState {

    private val viewModel: FollowViewModel by viewModels()

    private lateinit var userAdapter: UserAdapter

    override fun onViewBinding(container: ViewGroup?): FragmentPopularityBinding =
        FragmentPopularityBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initUser()
        initUserLoadState()

        val userRequest = UserRequest(
            username = login,
            perPage = 12,
            type = UserPagingSource.Type.FOLLOWING
        )
        viewModel.setEvent(FollowEvent.ListFollow(userRequest))
    }

    override fun onStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {

                when (it) {
                    is FollowState.UserData -> userAdapter.submitData(it.pagingData)
                }

            }
        }
    }

    private fun initUser() {
        userAdapter = UserAdapter {}

        viewBinding.apply {
            rvPopularity.layoutManager = LinearLayoutManager(requireActivity())
            rvPopularity.adapter = userAdapter.withLoadStateFooter(LoadStateAdapter())
        }
    }

    private fun initUserLoadState() {
        userAdapter.addLoadStateListener {
            val isLoading = it.refresh is LoadState.Loading
            viewBinding.apply {
                sflShimmerItemUser.isVisible = isLoading
                rvPopularity.isVisible = !isLoading

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

}