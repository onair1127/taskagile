package com.taskagile.web.results;

import java.util.HashMap;

import org.springframework.util.Assert;

public class ApiResult extends HashMap<String, Object> {
     private static final long serialVersionUID = 877825499039674411L;

     private static final String MESSAGE_KEY = "message";
     private static final String ERRORCODE_KEY = "errorReferenceCode";

     public static ApiResult blank() {
          return new ApiResult();
     }

     public static ApiResult message(String message) {
          Assert.hasText(message, "Parameter `message` must not be blank");

          ApiResult result = new ApiResult();
          result.put("message", message);
          return result;
     }

     public static ApiResult error(String message, String errorReferenceCode) {
          Assert.hasText(message, "Parameter `message` must not be blank");
          Assert.hasText(errorReferenceCode, "Parameter `errorReferenceCode must not be blank");

          ApiResult result = new ApiResult();
          result.put(MESSAGE_KEY, message);
          result.put(ERRORCODE_KEY, errorReferenceCode);
          return result;
     }

     public ApiResult add(String key, String value) {
          Assert.hasText(key, "Parameter `key` must not be blank");
          Assert.hasText(value, "Parameter `value` must not be blank");

          this.put(key, value);
          return this;
     }
}
