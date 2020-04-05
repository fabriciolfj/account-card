package br.com.accountcard.documents.util;

import br.com.accountcard.documents.domain.exception.JsonConvertException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static final <T> String getJson(T val)  {
        try {
            return new ObjectMapper().writeValueAsString(val);
        } catch (JsonProcessingException e) {
            throw new JsonConvertException(e.getMessage());
        }
    }
}
