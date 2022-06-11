package com.example.appnghenhac.Service;

public class APIService {
    private static String base_url = "http://192.168.1.69:8080/server/";

    public static  DataService getService (){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }

}
