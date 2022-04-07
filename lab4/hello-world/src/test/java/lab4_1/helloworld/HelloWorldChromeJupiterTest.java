package lab4_1.helloworld;

import static java.lang.invoke.MethodHandles.lookup;
// import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;

// import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class HelloWorldChromeJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver chrome;

    @BeforeEach
    void setup() {
        chrome = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        chrome.close();
    }

    @Test
    void test() {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        chrome.get(sutUrl);
        // WebElement href = firefox.findElement(By.xpath("//https://bonigarcia.dev/selenium-webdriver-java/"))
        

        // // Verify
        // assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
        // WebElement href = firefox.findElement(By.xpath("//a[@href='https://bonigarcia.dev/selenium-webdriver-java/']"));  
        String title = chrome.getTitle();
        System.out.println("The title of "+sutUrl+" is "+ title);
        log.debug("The title of {} is {}", sutUrl, title);      
        // assertTrue((href.isDisplayed())); 
    }

}