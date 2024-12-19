package com.htueko.tenki.core.domain.model.status

enum class ApiError(val httpCode: Int, val message: String) {
    UNAUTHORIZED(401, "API key not provided."),
    BAD_REQUEST_MISSING_Q(400, "Parameter 'q' not provided."),
    BAD_REQUEST_INVALID_URL(400, "API request url is invalid."),
    BAD_REQUEST_NO_LOCATION(400, "No location found matching parameter 'q'."),
    UNAUTHORIZED_INVALID_KEY(401, "API key provided is invalid."),
    FORBIDDEN_EXCEEDED_QUOTA(403, "API key has exceeded calls per month quota."),
    FORBIDDEN_DISABLED_KEY(403, "API key has been disabled."),
    FORBIDDEN_ACCESS_DENIED(403, "API key does not have access to the resource. Please check pricing page for what is allowed in your API subscription plan."),
    BAD_REQUEST_INVALID_JSON(400, "Json body passed in bulk request is invalid. Please make sure it is valid json with utf-8 encoding."),
    BAD_REQUEST_TOO_MANY_LOCATIONS(400, "Json body contains too many locations for bulk request. Please keep it below 50 in a single request."),
    INTERNAL_ERROR(400, "Internal application error.")
}
