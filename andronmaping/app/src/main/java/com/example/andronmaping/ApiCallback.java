package com.example.andronmaping;

public interface ApiCallback {
    void onSuccess(String response);

    void onError(String errorMessage);
}
