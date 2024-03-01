package com.github.fajaragungpramana.igit.module.favorite

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.common.contract.AppState
import com.github.fajaragungpramana.igit.databinding.FragmentFavoriteBinding
import com.github.fajaragungpramana.igit.module.adapter.LoadStateAdapter
import com.github.fajaragungpramana.igit.module.adapter.UserAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : AppFragment<FragmentFavoriteBinding>(), AppState {

    private val viewModel: FavoriteViewModel by viewModels()

    private lateinit var userAdapter: UserAdapter

    override fun onViewBinding(container: ViewGroup?): FragmentFavoriteBinding =
        FragmentFavoriteBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initFavorite()

        viewModel.setEvent(FavoriteEvent.ListUser)
    }

    override fun onStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {
                when (it) {
                    is FavoriteState.UserData -> userAdapter.submitData(it.pagingData)

                    is FavoriteState.MessageData ->
                        Snackbar.make(viewBinding.root, it.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initFavorite() {
        userAdapter = UserAdapter {
            val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(it.username)
            findNavController().navigate(action)
        }

        viewBinding.rvUser.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = userAdapter.withLoadStateFooter(LoadStateAdapter())
        }
    }

}