package com.learning.api.dataprovider.database.exception

class UsernameNotFoundException(
    username: String
) : java.lang.RuntimeException(
    "username=[$username] not found."
)