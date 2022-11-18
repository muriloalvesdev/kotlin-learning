package com.learning.api.dataprovider.database.exception

class UserAlreadyExistsException(
    val username: String
) : java.lang.RuntimeException(
    "username=[$username] already exists."
)