package com.github.fajaragungpramana.igit.module.popularity

import android.os.Bundle
import android.view.ViewGroup
import com.github.fajaragungpramana.igit.common.app.AppFragment
import com.github.fajaragungpramana.igit.databinding.FragmentPopularityBinding

class PopularityFragment : AppFragment<FragmentPopularityBinding>() {

    override fun onViewBinding(container: ViewGroup?): FragmentPopularityBinding =
        FragmentPopularityBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {

    }

}