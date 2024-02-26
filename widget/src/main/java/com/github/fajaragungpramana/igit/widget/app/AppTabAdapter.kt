package com.github.fajaragungpramana.igit.widget.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AppTabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val listFragment by lazy { arrayListOf<Fragment>() }

    fun addFragment(fragment: Fragment) {
        listFragment.add(fragment)
    }

    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment = listFragment[position]

}