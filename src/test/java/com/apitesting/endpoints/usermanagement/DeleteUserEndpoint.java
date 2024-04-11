package com.apitesting.endpoints.usermanagement;

import com.apitesting.apicontract.ApiEndpoint;
import com.apitesting.apicontract.HttpMethod;
import com.apitesting.apicontract.Parameter;
import com.apitesting.config.EndpointConfigReader;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.List;

public class DeleteUserEndpoint implements ApiEndpoint {

    String username;

    public DeleteUserEndpoint(String username){
        this.username=username;
    }

    @Override
    public String url() {
        return new EndpointConfigReader().getDeleteUserURLV2();
    }

    @Override
    public HttpMethod httpMethod() {
        return HttpMethod.DELETE;
    }

    @Override
    public String requestBody() {
        return null;
    }

    @Override
    public List<Parameter> queryParams() {
        return null;
    }

    @Override
    public List<Parameter> pathParams() {
        List<Parameter> pathParameters = new ArrayList<>();
        pathParameters.add(new Parameter("username",username));
        return pathParameters;
    }

    @Override
    public List<Parameter> headers() {

        List<Parameter> headers = new ArrayList<>();
        headers.add(new Parameter("accept",ContentType.JSON.toString()));
        return headers;
    }
}
