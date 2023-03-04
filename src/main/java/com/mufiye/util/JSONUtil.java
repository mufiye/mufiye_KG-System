package com.mufiye.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String getJsonFormat(Object target){
        try {
            objectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
            return objectMapper.writeValueAsString(target);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
