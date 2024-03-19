package com.apitesting.tests;

import com.apitesting.apicontract.ApiEndpoint;
import com.apitesting.clients.UserManagementAPIClient;
import com.apitesting.config.EndpointConfigReader;
import com.apitesting.dataproviders.UserManagementDp;
import com.apitesting.endpoints.usermanagement.CreateUserEndpoint;
import com.apitesting.helpers.ApiHelper;
import io.restassured.response.Response;
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
        System.out.println(createUserResponse.toString());



//        Response response=
//                given().log().all()
//                .header("Content-Type","application/json")
//                .body(postReqBody)
//                .when()
//                .post("/v2/user")
//                .then().log().all().extract().response();

    }

    @Test(dataProviderClass = UserManagementDp.class,dataProvider = "getUserDP")
    public void getMethodUserDetails(String userName){

        Response getUserResponse= umClient.getUserDetails(userName);
        System.out.println(getUserResponse.toString());

//        given().log().all()
//                .pathParam("username",userName)
//                .when()
//                .get("/v2/user/{username}")
//                .then().log().all()
//                .assertThat().statusCode(200);

    }



//    @Test
    public void deleteMethodUser(){

        given().log().all()
                .when()
                .delete("/v2/user/apitester2")
                .then().log().all()
                .assertThat().statusCode(200);

    }


//    @Test
    public void putMethodUpdateUser() throws IOException {


//        String putReqBody = file.readFileAsString("/src/main/resources/requestBody/userManagementAPIs/updateUserRequestBody.json");


        given().log().all()
                .header("Content-Type","application/json")
                .pathParam("userid","apitester")
                .body("")
                .when()
                .put("/v2/user/{userid}")
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
