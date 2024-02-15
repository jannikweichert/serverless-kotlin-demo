package com.serverless.port.`in`

import com.serverless.dto.UserDto

interface UserManagementUseCase {
    fun createUser(user: UserDto): UserDto?
    fun listUsers(): List<UserDto>
}
