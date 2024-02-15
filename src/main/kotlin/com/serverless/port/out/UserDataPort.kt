package com.serverless.port.out

import com.serverless.dto.UserDto

interface UserDataPort {
    fun saveUser(user: UserDto): UserDto
    fun findAllUsers(): List<UserDto>
}
