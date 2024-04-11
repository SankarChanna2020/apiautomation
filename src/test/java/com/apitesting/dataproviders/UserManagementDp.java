package com.apitesting.dataproviders;

import com.jayway.jsonpath.JsonPath;
import org.testng.annotations.DataProvider;
import com.apitesting.utilities.FileUtils;

public class UserManagementDp {

    FileUtils file = new FileUtils();

    String postReqBody = file.readFileAsString("/src/main/resources/requestBody/userManagementAPIs/createUserRequestBody.json");
    String updateReqBody = file.readFileAsString("/src/main/resources/requestBody/userManagementAPIs/updateUserRequestBody.json");


    @DataProvider(name="createUserDP")
    public Object[][] createUserDP(){

        postReqBody = JsonPath.parse(postReqBody)
                .set("$.id",Math.random())
                .set("$.email","sankar@gmail.com")
                .set("$.phone","9898989898")
                .jsonString();

        return new Object[][] {

                {postReqBody}
        };
    }

    @DataProvider(name="getUserDP")
    public Object[][] getUserDP(){

        return new Object[][] {

                {"apitester"},
                {"apitester2"},
                {"apitester3"},
        };
    }

    @DataProvider(name="deleteUserDP")
    public Object[][] deleteUserDP(){

        return new Object[][] {

                {"apitester"},
                {"apitester2"},
        };
    }


    @DataProvider(name="updateUserDP")
    public Object[][] updateUserDP(){

        updateReqBody = JsonPath.parse(updateReqBody)
                .set("$.email","sankarchanna@gmail.com")
                .set("$.phone","9898989898")
                .jsonString();

        return new Object[][] {

                {updateReqBody,"apitester"},
        };

    }


}
