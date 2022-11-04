package com.learning.api.dataprovider.database.exception

import java.lang.String.format

class UserAlreadyExistsException(
    val username: String
) : java.lang.RuntimeException(
    format(
        "username=[%s] already exists.", username
    )
)