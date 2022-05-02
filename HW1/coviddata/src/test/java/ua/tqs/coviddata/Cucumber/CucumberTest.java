package ua.tqs.coviddata.Cucumber;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.Suite;

@Suite
public class CucumberTest {
    @Test
    void assert1(){
        String actual = "true";
        assertEquals("true", actual);
    }
}
