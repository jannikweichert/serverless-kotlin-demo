package com.serverless.dto

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

data class UserDto(val id: String, val name: String, val email: String) {

    companion object {
        fun fromJson(json: String): UserDto {
            val mapper = jacksonObjectMapper()
            return mapper.readValue<UserDto>(json)
        }
    }
}
