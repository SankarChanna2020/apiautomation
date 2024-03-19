package com.apitesting.endpoints.usermanagement;

import com.apitesting.apicontract.ApiEndpoint;
import com.apitesting.apicontract.HttpMethod;
import com.apitesting.apicontract.Parameter;
import com.apitesting.config.EndpointConfigReader;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.List;

public class CreateUserEndpoint implements ApiEndpoint {


    String requestBody;

    public CreateUserEndpoint(String requestBody){

        this.requestBody = requestBody;

    }

    @Override
    public String url() {
        return new EndpointConfigReader().getCreateUserURLV2();
    }

    @Override
    public HttpMethod httpMethod() {
        return HttpMethod.POST;
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
    public List<Parameter> pathParams() {
        return null;
    }

    @Override
    public List<Parameter> headers() {

        List<Parameter> headers = new ArrayList<>();
        headers.add(new Parameter("Content-Type", ContentType.JSON.toString()));
        headers.add(new Parameter("accept",ContentType.JSON.toString()));
        return headers;
    }
}
