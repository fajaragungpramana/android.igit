package com.github.fajaragungpramana.igit.core.data.remote.user.request

import com.github.fajaragungpramana.igit.core.extension.removeNulls

data class UserRequest(
    var username: String? = null,
    var page: Int? = null
) : Map<String, Any> by mapOf(
    "q" to username,
    "page" to page
).removeNulls()
