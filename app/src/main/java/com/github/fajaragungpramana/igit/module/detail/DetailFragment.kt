package com.github.fajaragungpramana.igit.module.detail

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.github.fajaragungpramana.igit.R
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.common.contract.AppState
import com.github.fajaragungpramana.igit.core.domain.user.model.User
import com.github.fajaragungpramana.igit.databinding.FragmentDetailBinding
import com.github.fajaragungpramana.igit.module.follow.FollowFragment
import com.github.fajaragungpramana.igit.module.follower.FollowerFragment
import com.github.fajaragungpramana.igit.module.main.MainActivity
import com.github.fajaragungpramana.igit.module.repo.RepoFragment
import com.github.fajaragungpramana.igit.widget.app.AppTabAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : AppFragment<FragmentDetailBinding>(), AppState {

    private val viewModel: DetailViewModel by viewModels()

    private val login by lazy { arguments?.getString("login").orEmpty() }

    override fun onViewBinding(container: ViewGroup?): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initView()

        viewModel.setEvent(DetailEvent.User(username = login))
    }

    override fun onStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {
                when (it) {
                    is DetailState.UserLoading -> {
                        viewBinding.apply {
                            sflShimmerUser.isVisible = it.isLoading
                            llcUser.isVisible = !it.isLoading

                            if (it.isLoading) sflShimmerUser.startShimmer() else sflShimmerUser.stopShimmer()
                        }
                    }

                    is DetailState.UserData -> setUser(it.user)
                    is DetailState.MessageData -> Snackbar.make(
                        viewBinding.root,
                        it.message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun initView() {
        val adapter = AppTabAdapter(requireActivity())
        adapter.addFragment(RepoFragment.instance(login))
        adapter.addFragment(FollowerFragment.instance(login))
        adapter.addFragment(FollowFragment.instance(login))

        val listTabIcon = intArrayOf(
            R.drawable.ic_repositories_black,
            R.drawable.ic_followers_black,
            R.drawable.ic_following_black
        )

        viewBinding.vpUserPopularity.adapter = adapter
        viewBinding.vpUserPopularity.currentItem = 0
        TabLayoutMediator(
            viewBinding.tlUserPopularity,
            viewBinding.vpUserPopularity
        ) { tab, position ->
            tab.icon = AppCompatResources.getDrawable(requireActivity(), listTabIcon[position])
        }.attach()
    }

    private fun setUser(user: User) {
        (requireActivity() as MainActivity).apply {
            title = user.username
        }
        viewBinding.apply {
            aivUserAvatar.load(user.avatar) { transformations(CircleCropTransformation()) }
            mtvUserFullName.text = user.fullName

            mtvUserBio.isVisible = !user.bio.isNullOrEmpty()
            mtvUserBio.text = user.bio

            mtvUserEmail.isVisible = !user.email.isNullOrEmpty()
            mtvUserEmail.text = user.email

            itpUserRepository.content = user.totalRepository.toString()
            itpUserFollower.content = user.totalFollower.toString()
            itpUserFollowing.content = user.totalFollowing.toString()
        }
    }

    override fun onDestroyView() {
        (requireActivity() as MainActivity).title = getString(R.string.app_name)

        super.onDestroyView()
    }

}