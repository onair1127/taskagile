package com.taskagile.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fasterxml의 ObjectMapper를 활용하여 object를 Json String으로 변환
 */
public class JsonUtils {
     private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

     public static String toJson(Object object) {
          ObjectMapper mapper = new ObjectMapper();

          try {
               return mapper.writeValueAsString(object);

          } catch(JsonProcessingException e) {
               log.error("Failed to convert Object to JSON string.", e);
               return null;
          }
     }
}
