package com.erzhiqian.wechatappmanager.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.json.JsonMapper
import org.springframework.http.ResponseEntity

class ResponseBodyReader {

    private JsonMapper mapper = new JsonMapper()


    private <B> B getBody(ResponseEntity<String> response, TypeReference<B> type) {
        try {
            mapper.readValue(response.body, type)
        } catch (Exception e) {
            null
        }
    }
}
