package com.github.fajaragungpramana.igit.core.data.remote.user.request

import com.github.fajaragungpramana.igit.core.data.remote.user.UserPagingSource
import com.github.fajaragungpramana.igit.core.extension.removeNulls

data class UserRequest(
    var q: String? = null,
    var page: Int? = null,
    var perPage: Int? = null,
    var username: String? = null,
    var type: UserPagingSource.Type? = null
) : Map<String, Any> by mapOf(
    "q" to q,
    "page" to page,
    "per_page" to perPage
).removeNulls()
