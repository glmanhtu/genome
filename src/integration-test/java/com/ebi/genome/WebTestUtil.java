package com.ebi.genome;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class WebTestUtil {

    /**
     * Prevents instantiation
     */
    private WebTestUtil() {}

    /**
     * Transforms an object into JSON and returns the JSON as a byte array.
     * @param object    The object that is transformed into JSON.
     * @return          The JSON representation of an object as a byte array.
     * @throws IOException
     */
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
