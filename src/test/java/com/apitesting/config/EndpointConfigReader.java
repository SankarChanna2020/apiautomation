package com.apitesting.config;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.apitesting.utilities.FileUtils;

public class EndpointConfigReader {

    DocumentContext jsonConfig;

    public EndpointConfigReader(){

        FileUtils file = new FileUtils();
        String endpointsConfig= file.getEndpointConfigFileAsString();
        jsonConfig = JsonPath.parse(endpointsConfig);

    }

    private String getUserManagementHostURL(){
        return jsonConfig.read("$.apiEndpoints.userManagementAPIs.endpoint");
    }

    private String getResourcePaths(String apiName){
        return jsonConfig.read(String.format("$.apiEndpoints.userManagementAPIs.resourcePaths.%s",apiName)).toString();
    }

    public String getCreateUserURLV2(){
        return getUserManagementHostURL() + getResourcePaths("createUserV2");
    }

    public String getUpdateUserURLV2(){
        return getUserManagementHostURL() + getResourcePaths("updateUserV2");
    }

    public String getFetchUserURLV2(){
        return getUserManagementHostURL() + getResourcePaths("getUserDetailsV2");
    }

    public String getDeleteUserURLV2(){
        return getUserManagementHostURL() + getResourcePaths("deleteUserV2");
    }



}
