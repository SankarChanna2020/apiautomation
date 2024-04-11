package com.apitesting.clients;

import com.apitesting.apicontract.ApiEndpoint;
import com.apitesting.endpoints.usermanagement.CreateUserEndpoint;
import com.apitesting.endpoints.usermanagement.DeleteUserEndpoint;
import com.apitesting.endpoints.usermanagement.GetUserEndpoint;
import com.apitesting.endpoints.usermanagement.UpdateUserEndpoint;
import com.apitesting.helpers.ApiHelper;
import io.restassured.response.Response;

public class UserManagementAPIClient {

    public Response createUser(String reqBody){
        ApiEndpoint endpoint = new CreateUserEndpoint(reqBody);
        return new ApiHelper().executeAPIRequest(endpoint);
    }

    public Response getUserDetails(String username){
        ApiEndpoint endpoint = new GetUserEndpoint(username);
        return new ApiHelper().executeAPIRequest(endpoint);
    }

    public Response deleteUser(String username){
        ApiEndpoint endpoint = new DeleteUserEndpoint(username);
        return new ApiHelper().executeAPIRequest(endpoint);
    }

    public Response updateUser(String reqBody,String username){
        ApiEndpoint endpoint = new UpdateUserEndpoint(reqBody,username);
        return new ApiHelper().executeAPIRequest(endpoint);
    }
}
