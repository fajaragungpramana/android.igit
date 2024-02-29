package com.github.fajaragungpramana.igit.module.favorite

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : AppFragment<FragmentFavoriteBinding>() {

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onViewBinding(container: ViewGroup?): FragmentFavoriteBinding =
        FragmentFavoriteBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {

    }

}