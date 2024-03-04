package com.github.fajaragungpramana.igit.module.setting

import android.os.Bundle
import android.view.ViewGroup
import com.github.fajaragungpramana.igit.R
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.databinding.FragmentSettingBinding
import com.github.fajaragungpramana.igit.module.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : AppFragment<FragmentSettingBinding>() {

    override fun onViewBinding(container: ViewGroup?): FragmentSettingBinding =
        FragmentSettingBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        (requireActivity() as MainActivity).apply {
            title = getString(R.string.settings)
        }
    }

    override fun onDestroyView() {
        (requireActivity() as MainActivity).title = getString(R.string.app_name)

        super.onDestroyView()
    }

}