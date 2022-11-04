package com.learning.api.dataprovider.database.exception

import java.lang.String.format

class UsernameNotFoundException(
    username: String
) : java.lang.RuntimeException(
    format("username=[%s] not found.", username)
)