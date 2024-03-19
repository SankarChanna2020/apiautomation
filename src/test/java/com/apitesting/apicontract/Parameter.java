package com.apitesting.apicontract;

public class Parameter {

    private String Key;
    private String Value;

    public Parameter(String key ,String value){
        this.Key = key;
        this.Value= value;
    }

    public String getKey(){
        return Key;
    }

    public void setKey(String key){
        this.Key=key;
    }

    public String getValue(){

        return Value;
    }

    public void setValue(String value){

        this.Value = value;
    }

    public Parameter clone(){
        return  new Parameter(this.getKey(),this.getValue());
    }
}
