package com.apitesting.apicontract;

import java.util.List;

public interface ApiEndpoint {
    String url();
    HttpMethod httpMethod();
    String requestBody();
    List<Parameter> queryParams();
    List<Parameter> pathParams();
    List<Parameter> headers();

}
