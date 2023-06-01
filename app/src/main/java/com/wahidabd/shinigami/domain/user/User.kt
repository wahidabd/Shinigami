package com.wahidabd.shinigami.domain.user

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 6/1/2023.
 * Github github.com/wahidabd.
 */


data class User(
    val name: String? = emptyString(),
    val email: String? = emptyString(),
    val avatar: String? = emptyString()
)