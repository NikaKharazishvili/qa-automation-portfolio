package reqres.tests;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import reqres.config.ApiConfig;

/**
 * BaseTest is the base class for all API tests.
 * It initializes the common RequestSpecification before any test methods run.
 */
public class BaseTest {
    protected RequestSpecification request;

    @BeforeClass
    public void setUp() {
        request = ApiConfig.getRequestSpec();
    }
}