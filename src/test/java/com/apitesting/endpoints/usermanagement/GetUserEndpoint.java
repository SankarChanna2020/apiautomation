package com.apitesting.endpoints.usermanagement;

import com.apitesting.apicontract.ApiEndpoint;
import com.apitesting.apicontract.HttpMethod;
import com.apitesting.apicontract.Parameter;
import com.apitesting.config.EndpointConfigReader;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.List;

public class GetUserEndpoint implements ApiEndpoint {

    String username;

    public GetUserEndpoint(String username){
        this.username=username;
    }

    @Override
    public String url() {
        return new EndpointConfigReader().getFetchUserURLV2();
    }

    @Override
    public HttpMethod httpMethod() {
        return HttpMethod.GET;
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
