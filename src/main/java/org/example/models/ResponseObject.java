package org.example.models;

import lombok.Data;

@Data
public class ResponseObject<T> {
    T responseObject;
    int responseCode;

    public ResponseObject(T misterClass, int responseCode) {
        this.responseObject = misterClass;
        this.responseCode = responseCode;
    }
}
