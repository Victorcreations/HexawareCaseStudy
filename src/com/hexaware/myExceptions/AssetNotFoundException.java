package com.hexaware.myExceptions;

public class AssetNotFoundException extends Exception{

    public AssetNotFoundException(){}

    public AssetNotFoundException(String message){
        super(message);
    }

}
