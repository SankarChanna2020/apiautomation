package com.apitesting.endpoints.usermanagement;

import com.apitesting.apicontract.ApiEndpoint;
import com.apitesting.apicontract.HttpMethod;
import com.apitesting.apicontract.Parameter;
import com.apitesting.config.EndpointConfigReader;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.List;

public class UpdateUserEndpoint implements ApiEndpoint {


    String requestBody;
    String username;

    public UpdateUserEndpoint(String requestBody,String username){

        this.requestBody = requestBody;
        this.username=username;

    }

    @Override
    public String url() {
        return new EndpointConfigReader().getUpdateUserURLV2();
    }

    @Override
    public HttpMethod httpMethod() {
        return HttpMethod.PUT;
    }

    @Override
    public String requestBody() {
        return requestBody;
    }

    @Override
    public List<Parameter> queryParams() {
        return null;
    }

    @Override
    public List<Parameter> pathParams(){
        List<Parameter> pathParameters = new ArrayList<>();
        pathParameters.add(new Parameter("username",username));
        return pathParameters;
    }

    @Override
    public List<Parameter> headers() {

        List<Parameter> headers = new ArrayList<>();
        headers.add(new Parameter("Content-Type", ContentType.JSON.toString()));
        headers.add(new Parameter("accept",ContentType.JSON.toString()));
        return headers;
    }
}
