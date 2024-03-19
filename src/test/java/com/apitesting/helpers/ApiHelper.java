package com.apitesting.helpers;

import com.apitesting.apicontract.ApiEndpoint;
import com.apitesting.apicontract.HttpMethod;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {


    public Response executeAPIRequest(ApiEndpoint apiEndpoint){
        return executeRequest(apiEndpoint);
    }

    public Response executeRequest(ApiEndpoint apiEndpoint){

//        String endpoint = apiEndpoint.getClass().getSimpleName().replaceAll("Endpoint","");

        String url = apiEndpoint.url();
        HttpMethod httpMethod = apiEndpoint.httpMethod();

        //API Request method :
        RequestSpecification request = requestSpecification(apiEndpoint);
        Response response = fetchAPIResponse(url,httpMethod,request);

        return response;
    }

    private RequestSpecification requestSpecification(ApiEndpoint apiEndpoint){

        RequestSpecification request = given();

        if (apiEndpoint.queryParams() != null) {

            apiEndpoint.queryParams().forEach(queryParams -> request.queryParams(queryParams.getKey(),queryParams.getValue()));

        }
        if (apiEndpoint.pathParams() != null) {

            apiEndpoint.pathParams().forEach(pathParams -> request.pathParams(pathParams.getKey(),pathParams.getValue()));

        }
        if (apiEndpoint.headers() != null) {

            apiEndpoint.headers().forEach(headers -> request.headers(headers.getKey(),headers.getValue()));

        }
        if (apiEndpoint.requestBody()!=null){
            request.body(apiEndpoint.requestBody());
        }

        return request;
    }

   private Response fetchAPIResponse(String url ,HttpMethod httpMethod,RequestSpecification request){

        Response response = null;

        switch (httpMethod){
            case POST:
                response = request.post(url);
                break;
            case GET:
                response = request.get(url);
                break;
            case PUT:
                response = request.put(url);
                break;
            case DELETE:
                response = request.delete(url);
                break;
            case PATCH:
                response = request.patch(url);
                break;
        }

       return response;
   }







}
