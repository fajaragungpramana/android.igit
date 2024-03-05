package com.github.fajaragungpramana.igit.module.setting

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.fajaragungpramana.igit.R
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.common.contract.AppState
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.SettingEntity
import com.github.fajaragungpramana.igit.databinding.FragmentSettingBinding
import com.github.fajaragungpramana.igit.module.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : AppFragment<FragmentSettingBinding>(), AppState {

    private val viewModel: SettingViewModel by viewModels()

    override fun onViewBinding(container: ViewGroup?): FragmentSettingBinding =
        FragmentSettingBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initView()

        viewModel.setEvent(SettingEvent.Setting)
    }

    private fun initView() {
        (requireActivity() as MainActivity).apply {
            title = getString(R.string.settings)
        }
    }

    override fun onStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {
                when (it) {

                    is SettingState.SettingData -> {
                        if (it.listSetting.isEmpty())
                            viewModel.setEvent(
                                SettingEvent.SaveSetting(
                                    SettingEntity(
                                        title = "Theme",
                                        overview = "Determine the theme of the application that you want to use, light/dark",
                                        code = SettingEntity.Code.THEME.name
                                    )
                                )
                            )
                    }

                    is SettingState.MessageData ->
                        Snackbar.make(viewBinding.root, it.message, Snackbar.LENGTH_LONG).show()

                }
            }
        }
    }

    override fun onDestroyView() {
        (requireActivity() as MainActivity).title = getString(R.string.app_name)

        super.onDestroyView()
    }

}