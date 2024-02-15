package com.serverless.domain

import com.serverless.adapter.secondary.UserDynamoAdapter
import com.serverless.dto.UserDto
import com.serverless.port.`in`.UserManagementUseCase
import com.serverless.port.out.UserDataPort

class UserService: UserManagementUseCase {

    private val userDataPort: UserDataPort = UserDynamoAdapter()

    override fun createUser(user: UserDto) = userDataPort.saveUser(user)


    override fun listUsers(): List<UserDto> {
        TODO("Not yet implemented")
    }
}
