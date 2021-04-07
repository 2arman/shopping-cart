package com.example.shoppingcart.integration.config;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static java.lang.ThreadLocal.withInitial;

/**
 * Singleton to manage objects and share their state between step definitions.
 */
public enum CucumberTestContext {
    CONTEXT;

    private static final String PAYLOAD = "PAYLOAD";
    private static final String REQUEST = "REQUEST";
    private static final String RESPONSE = "RESPONSE";

    private final ThreadLocal<Map<String, Object>> contextMap = withInitial(HashMap::new);

    private Map<String, Object> testContextMap() {
        return contextMap.get();
    }

    public void set(String key, Object value) {
        testContextMap().put(key, value);
    }

    public Object get(String key) {
        return testContextMap().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(testContextMap().get(key));
    }

    public Object getPayload() {
        return testContextMap().get(PAYLOAD);
    }

    public void setPayload(Object value) {
        set(PAYLOAD, value);
    }

    public <T> T getPayload(Class<T> clazz) {
        return get(PAYLOAD, clazz);
    }

    public RequestSpecification getRequest() {
        return get(REQUEST, RequestSpecification.class);
    }

    public Response getResponse() {
        return get(RESPONSE, Response.class);
    }

    public void setResponse(Response response) {
        set(RESPONSE, response);
    }

    public void reset() {
        testContextMap().clear();
    }

}
