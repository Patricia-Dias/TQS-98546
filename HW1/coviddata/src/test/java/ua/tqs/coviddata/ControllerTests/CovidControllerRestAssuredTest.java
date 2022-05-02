package ua.tqs.coviddata.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import ua.tqs.coviddata.Controller.CovidDataController;
@WebMvcTest(CovidDataController.class)
class CovidControllerRestAssuredTest {
    @Test
    void assert1(){
        String actual = "true";
        assertEquals("true", actual);
    }
}
