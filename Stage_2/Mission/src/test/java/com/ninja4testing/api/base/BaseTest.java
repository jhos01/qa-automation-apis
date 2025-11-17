package com.ninja4testing.api.base;

import com.ninja4testing.api.config.Config;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = Config.BASE_URL;
    }
}
