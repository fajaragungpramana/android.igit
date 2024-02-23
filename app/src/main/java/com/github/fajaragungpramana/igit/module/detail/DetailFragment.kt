package com.github.fajaragungpramana.igit.module.detail

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.common.contract.AppState
import com.github.fajaragungpramana.igit.core.domain.user.model.User
import com.github.fajaragungpramana.igit.databinding.FragmentDetailBinding
import com.github.fajaragungpramana.igit.module.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : AppFragment<FragmentDetailBinding>(), AppState {

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewBinding(container: ViewGroup?): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        viewModel.setEvent(DetailEvent.User(username = "fajaragungpramana"))
    }

    override fun onStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {
                when (it) {
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

    private fun setUser(user: User) {
        (requireActivity() as MainActivity).apply {
            title = user.username
        }
        viewBinding.apply {
            aivUserAvatar.load(user.avatar) { transformations(CircleCropTransformation()) }
            mtvUserFullName.text = user.fullName
        }
    }

}