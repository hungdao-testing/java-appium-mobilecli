package auto.mobile.formcli.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonConverter {

    public static <T> T parseJsonAs(String jsonFile, Class<T> model) {
        try {
            return new ObjectMapper().readValue(new File(jsonFile), model);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseJsonAs(String jsonFile, TypeReference<T> model) {
        try {
            return new ObjectMapper().readValue(new File(jsonFile), model);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
