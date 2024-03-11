import com.apitesting.dataproviders.UserManagementDp;
import io.restassured.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.FileUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;


public class APIDemo {


    @BeforeTest
    public void baseURL(){

        RestAssured.baseURI="https://petstore.swagger.io";

    }

    @Test(dataProviderClass = UserManagementDp.class,dataProvider = "createUserDP")
    public void postMethodCreateUserAPI(String postReqBody) throws IOException {


        given().log().all()
                .header("Content-Type","application/json")
                .body(postReqBody)
                .when()
                .post("/v2/user")
                .then().log().all()
                .assertThat().statusCode(200);

    }

    @Test(dataProviderClass = UserManagementDp.class,dataProvider = "getUserDP")
    public void getMethodUserDetails(String userName){

        given().log().all()
                .pathParam("username",userName)
                .when()
                .get("/v2/user/{username}")
                .then().log().all()
                .assertThat().statusCode(200);

    }

    @Test
    public void deleteMethodUser(){

        given().log().all()
                .when()
                .delete("/v2/user/apitester2")
                .then().log().all()
                .assertThat().statusCode(200);

    }

    public void deleteMethodUser1(){

        given().log().all()
                .when()
                .delete("/v2/user/apitester2")
                .then().log().all()
                .assertThat().statusCode(200);

    }


    @Test
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
