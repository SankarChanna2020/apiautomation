package com.apitesting.helpers;

import com.apitesting.apicontract.ApiEndpoint;
import com.apitesting.apicontract.HttpMethod;
import com.apitesting.apicontract.Parameter;
import io.qameta.allure.Attachment;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Reporter;

import static io.restassured.RestAssured.given;

public class ApiHelper {


    public Response executeAPIRequest(ApiEndpoint apiEndpoint){
        return executeRequest(apiEndpoint);
    }

    public Response executeRequest(ApiEndpoint apiEndpoint){

        String endpointName = apiEndpoint.getClass().getSimpleName().replaceAll("Endpoint","");
        String url = apiEndpoint.url();
        HttpMethod httpMethod = apiEndpoint.httpMethod();

        //calling logging request data method
        loggingRequestData(apiEndpoint,endpointName,url,httpMethod,apiEndpoint.requestBody());

        //API Request method :
        RequestSpecification request = requestSpecification(apiEndpoint);
        Response response = fetchAPIResponse(url,httpMethod,request);

        // calling logging response data method
        loggingResponseData(endpointName,response);

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

   private void loggingRequestData(ApiEndpoint apiEndpoint,String endpointName,String url,HttpMethod httpMethod,String reqbody){

        //url formatting
        String urlLogging = String.format("\n" + endpointName +" URL ---> %s %s",httpMethod.toString(),url);
        Reporter.log(urlLogging,true);
        loggingToAllureReport(urlLogging);



        //header formatting
        if(apiEndpoint.headers() != null){

            String headerLogging = "";
            for(Parameter p : apiEndpoint.headers()){
                headerLogging = headerLogging + String.format("%s:%s\n",p.getKey(),p.getValue());
            }

            String headerMessage = String.format(endpointName + " Header --> \n%s",headerLogging);
            Reporter.log(headerMessage.substring(0,headerMessage.length()-1),true);
            loggingToAllureReport(headerMessage);


        }

        //queryParams  formatting
       if(apiEndpoint.queryParams() != null){

           String queryParamLogging = "?";
           for(Parameter p : apiEndpoint.queryParams()){
               queryParamLogging = queryParamLogging + String.format("%s:%s\n",p.getKey(),p.getValue());
           }

           String queryParamsMessage = String.format(endpointName + " Query Parameters --> \n%s",queryParamLogging);
           Reporter.log(queryParamsMessage.substring(0,queryParamsMessage.length()-1),true);
           loggingToAllureReport(queryParamsMessage);


       }

        //pathParams formatting
       if(apiEndpoint.pathParams() != null){

           for(Parameter p : apiEndpoint.pathParams()){
               String pathParamLogging = String.format("%s:%s\n",p.getKey(),p.getValue());
               String pathParamsMessage = String.format(endpointName + " Path Parameters --> \n%s",pathParamLogging);
               Reporter.log(pathParamsMessage,true);
               loggingToAllureReport(pathParamsMessage);
           }

       }
       //request body

       if(reqbody != null){

           String requestBodyLogging = String.format(endpointName + " Request Body --> %s",reqbody);
           Reporter.log(requestBodyLogging,true);
           loggingToAllureReport(requestBodyLogging);

       }

   }

   public void loggingResponseData(String endpointName,Response response){

        String responseMessage=String.format(endpointName +" Response [%s]--> %s",response.getStatusCode(),response.asString());
        Reporter.log(responseMessage,true);
        loggingToAllureReport(responseMessage);
   }

    @Attachment(value = "{0}" , type="text/plain")
    public String loggingToAllureReport(String data){
        return  data;
    }




}
