package com.github.fajaragungpramana.igit.module.repo

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.common.contract.AppState
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.databinding.FragmentPopularityBinding
import com.github.fajaragungpramana.igit.module.adapter.RepoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepoFragment(private val login: String) : AppFragment<FragmentPopularityBinding>(), AppState {

    private val viewModel: RepoViewModel by viewModels()

    private lateinit var repoAdapter: RepoAdapter

    override fun onViewBinding(container: ViewGroup?): FragmentPopularityBinding =
        FragmentPopularityBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initRepo()

        val userRequest = UserRequest(username = login, perPage = 12)
        viewModel.setEvent(RepoEvent.ListRepo(userRequest))
    }

    override fun onStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {
                when (it) {
                    is RepoState.RepoData -> repoAdapter.submitData(it.pagingData)
                }
            }
        }
    }

    private fun initRepo() {
        repoAdapter = RepoAdapter()

        viewBinding.apply {
            rvPopularity.hasFixedSize()
            rvPopularity.layoutManager = LinearLayoutManager(requireActivity())
            rvPopularity.adapter = repoAdapter
        }
    }

}