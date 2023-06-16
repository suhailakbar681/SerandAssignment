package com.serand.assignment.common.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse {

    private Object data;
    private String message;
    private Integer errorCode;
    private Boolean status;

    public static RestResponse of(Object data) {
        RestResponse authResponse = new RestResponse();
        authResponse.setData(data);
        authResponse.setStatus(Boolean.TRUE);
        return authResponse;
    }

    public static RestResponse of(Object data, String message) {
        RestResponse authResponse = new RestResponse();
        authResponse.setData(data);
        authResponse.setStatus(Boolean.TRUE);
        authResponse.setMessage(message);
        return authResponse;
    }

    public static RestResponse of(Object data, int errorCode, String message) {
        RestResponse authResponse = new RestResponse();
        authResponse.setData(data);
        authResponse.setStatus(Boolean.TRUE);
        authResponse.setErrorCode(errorCode);
        authResponse.setMessage(message);
        return authResponse;
    }

    public static RestResponse fail(String message) {
        RestResponse authResponse = new RestResponse();
        authResponse.setMessage(message);
        authResponse.setStatus(Boolean.FALSE);
        return authResponse;
    }

    public static RestResponse fail(int errorCode, String message) {
        RestResponse authResponse = new RestResponse();
        authResponse.setErrorCode(errorCode);
        authResponse.setMessage(message);
        authResponse.setStatus(Boolean.FALSE);
        return authResponse;
    }

    public static RestResponse success(String message) {
        RestResponse authResponse = new RestResponse();
        authResponse.setMessage(message);
        authResponse.setStatus(Boolean.TRUE);
        return authResponse;
    }
}