package com.erzhiqian.wechatappmanager.specification


import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract class BasicSpecification extends Specification {

    void setupSpec() {
        fixWireMock()
    }

    private static void fixWireMock() {
        System.setProperty('http.keepAlive', 'false')
        System.setProperty('http.maxConnections', '1')
    }
}