package com.apitesting.tests;

import com.apitesting.apicontract.ApiEndpoint;
import com.apitesting.clients.UserManagementAPIClient;
import com.apitesting.config.EndpointConfigReader;
import com.apitesting.dataproviders.UserManagementDp;
import com.apitesting.endpoints.usermanagement.CreateUserEndpoint;
import com.apitesting.helpers.ApiHelper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;


public class UserManagementTests {

    private UserManagementAPIClient umClient;

    UserManagementTests(){
        umClient = new UserManagementAPIClient();
    }


    @Test(dataProviderClass = UserManagementDp.class,dataProvider = "createUserDP")
    public void postMethodCreateUserAPI(String postReqBody) throws IOException {

        Response createUserResponse= umClient.createUser(postReqBody);
        Assert.assertEquals(createUserResponse.getStatusCode(),HttpStatus.SC_OK);

    }

    @Test(dataProviderClass = UserManagementDp.class,dataProvider = "getUserDP")
    public void getMethodUserDetails(String userName){

        Response getUserResponse= umClient.getUserDetails(userName);
        Assert.assertEquals(getUserResponse.getStatusCode(),HttpStatus.SC_OK);

    }


    @Test(dataProviderClass = UserManagementDp.class,dataProvider = "updateUserDP")
    public void putMethodUpdateUser(String reqbody,String userName) throws IOException {

        Response updateUserResponse= umClient.updateUser(reqbody,userName);
        Assert.assertEquals(updateUserResponse.getStatusCode(),HttpStatus.SC_OK);

    }

    @Test(dataProviderClass = UserManagementDp.class,dataProvider = "deleteUserDP")
    public void deleteMethodUser(String userName){

        Response deleteUserResponse= umClient.deleteUser(userName);
        Assert.assertEquals(deleteUserResponse.getStatusCode(),HttpStatus.SC_OK);


    }
}
