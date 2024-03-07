package com.github.fajaragungpramana.igit.module.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.fajaragungpramana.igit.common.app.AppListAdapter
import com.github.fajaragungpramana.igit.common.app.AppRecyclerViewHolder
import com.github.fajaragungpramana.igit.core.domain.local.model.Setting
import com.github.fajaragungpramana.igit.databinding.ItemSettingBinding

class SettingAdapter(private val onItemSwitch: (Setting) -> Unit) :
    AppListAdapter<ItemSettingBinding, Setting, SettingAdapter.ViewHolder>(Setting.diffUtil) {

    override fun onViewBinding(viewGroup: ViewGroup): ItemSettingBinding =
        ItemSettingBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

    override fun onViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    inner class ViewHolder(itemView: View) : AppRecyclerViewHolder<Setting>(itemView) {

        override fun bindItem(item: Setting, position: Int) {
            viewBinding.mtvSettingTitle.text = item.title
            viewBinding.mtvSettingOverview.text = item.overview
            viewBinding.smSetting.isChecked = item.isEnable

            viewBinding.smSetting.setOnCheckedChangeListener { _, _ ->
                onItemSwitch.invoke(item)
            }
        }

    }

}