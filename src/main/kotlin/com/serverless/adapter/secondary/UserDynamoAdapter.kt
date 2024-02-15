package com.serverless.adapter.secondary

import aws.sdk.kotlin.runtime.auth.credentials.EnvironmentCredentialsProvider
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import aws.sdk.kotlin.services.dynamodb.model.PutItemRequest
import aws.sdk.kotlin.services.dynamodb.model.PutItemResponse
import aws.sdk.kotlin.services.dynamodb.model.ReturnValue
import com.serverless.dto.UserDto
import com.serverless.port.out.UserDataPort
import kotlinx.coroutines.runBlocking


class UserDynamoAdapter: UserDataPort {

    private val userTable = System.getenv("USERS_TABLE")
    private val dynamoDbClient = DynamoDbClient {
        region = System.getenv("AWS_REGION")
        credentialsProvider = EnvironmentCredentialsProvider()
    }
    override fun saveUser(user: UserDto): UserDto {
        println("userTable: $userTable")
        val itemValues = mapOf(
            "id" to AttributeValue.S(user.id),
            "name" to AttributeValue.S(user.name),
            "email" to AttributeValue.S(user.email)
        )

        val response: PutItemResponse = runBlocking {
            dynamoDbClient.putItem(
                PutItemRequest {
                    tableName = userTable
                    item = itemValues
                    returnValues = ReturnValue.AllOld
                }
            )
        }

        println("response: $response");

        return UserDto(
            id = response.attributes?.get("id")?.asS()?:"",
            name = response.attributes?.get("name")?.asS()?:"",
            email = response.attributes?.get("email")?.asS()?:""
        )
    }

    override fun findAllUsers(): List<UserDto> {
        TODO("Not yet implemented")
    }


}
