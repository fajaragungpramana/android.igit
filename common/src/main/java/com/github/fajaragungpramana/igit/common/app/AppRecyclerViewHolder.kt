package com.github.fajaragungpramana.igit.common.app

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AppRecyclerViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bindItem(item: T, position: Int)

}