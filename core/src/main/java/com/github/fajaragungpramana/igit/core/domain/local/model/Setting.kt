package com.github.fajaragungpramana.igit.core.domain.local.model

import androidx.recyclerview.widget.DiffUtil
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.SettingEntity

data class Setting(
    var id: Int? = null,
    var title: String? = null,
    var overview: String? = null,
    var code: SettingEntity.Code
) {

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Setting>() {

            override fun areContentsTheSame(oldItem: Setting, newItem: Setting): Boolean =
                oldItem.id == newItem.id

            override fun areItemsTheSame(oldItem: Setting, newItem: Setting): Boolean =
                oldItem == newItem

        }

        fun mapToList(listSettingEntity: List<SettingEntity>): List<Setting> {
            val list = arrayListOf<Setting>()
            listSettingEntity.forEach {
                list.add(
                    Setting(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        code = SettingEntity.Code.valueOf(it.code)
                    )
                )
            }

            return list
        }

    }

}