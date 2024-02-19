package com.github.fajaragungpramana.igit.extension

import android.content.Context
import android.content.Intent

inline fun <reified T> Context.startActivity(vararg data: Pair<String, Any>) {
    val i = Intent(this, T::class.java)
    startActivity(i)
}