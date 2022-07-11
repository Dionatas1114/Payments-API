package com.api.payments.indicator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum HttpMethods {

    GET_ONE("Get"),
    GET_ALL("Get"),
    POST("Post"),
    PUT("Put"),
    DELETE("Delete");

    private String value;

    @Override
    public String toString() {
        return this.value;
    }
}
