package com.serverless.adapter.primary.apigateway.user

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.serverless.adapter.primary.apigateway.ApiGatewayResponse
import com.serverless.domain.UserService
import com.serverless.dto.UserDto
import com.serverless.port.`in`.UserManagementUseCase
import org.apache.logging.log4j.LogManager

class CreateUserHandler : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

    private val userManagement: UserManagementUseCase = UserService();

    override fun handleRequest(input: Map<String, Any>, context: Context): ApiGatewayResponse {
        LOG.info("received body: " + input["body"].toString())

        val createUserDto = UserDto.fromJson(input["body"].toString())

        val result = userManagement.createUser(createUserDto);
        var objectBody: UserResponse = UserResponse(id = null, name = "")

        var statusCode = 404
        if (result != null) {
            statusCode = 200
            objectBody = UserResponse(result.id, result.name)
        }
        return ApiGatewayResponse.build {
            statusCode = statusCode
            objectBody = objectBody
            headers = mapOf("X-Powered-By" to "AWS Lambda & serverless")
        }
    }

    companion object {
        private val LOG = LogManager.getLogger(CreateUserHandler::class.java)
    }
}
