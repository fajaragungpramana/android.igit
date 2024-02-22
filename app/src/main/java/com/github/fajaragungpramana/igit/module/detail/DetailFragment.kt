package com.github.fajaragungpramana.igit.module.detail

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.databinding.FragmentDetailBinding
import com.github.fajaragungpramana.igit.module.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : AppFragment<FragmentDetailBinding>() {

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewBinding(container: ViewGroup?): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        (requireActivity() as MainActivity).apply {
            setSupportActionBar(viewBinding.mtlDetail)
            supportActionBar?.let {
                it.setDisplayHomeAsUpEnabled(true)
                it.title = "Fajar Agung Pramana"
            }
        }
        viewBinding.mtlDetail.setNavigationOnClickListener { findNavController().navigateUp() }
    }

}