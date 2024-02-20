package com.github.fajaragungpramana.igit.core.extension

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Map<String, T?>.removeNulls(): Map<String, T> {
    return filterValues { it != null } as Map<String, T>
}