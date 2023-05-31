package com.example.andronmaping;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionSpec;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiManager {
    private OkHttpClient client;
    private String baseUrl;

    ApiManager(String baseUrl){
        this.baseUrl = baseUrl;
        client = new OkHttpClient.Builder() .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT,ConnectionSpec.MODERN_TLS,ConnectionSpec.COMPATIBLE_TLS))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .build();
    }

    public void post(String ipAddr, String route, final ApiCallback callback){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ip", ""+ipAddr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url(baseUrl+route)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String res = response.body().string();
                    try {
                        String str = "";
                        JSONObject Jobject = new JSONObject(res);
                        for(int i = 0; i<Jobject.names().length(); i++){
                            str = str + Jobject.getString(""+i) + "\n";
                        }
                        callback.onSuccess(str);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }else{
                    callback.onError("HTTP Error: "+response.code());
                }
            }
        });
    }

}

