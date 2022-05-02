package ua.tqs.coviddata.Cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CovidDataSteps {
    private WebDriver driver;

    //test workflow
    @When("I navigate to {string}")
    public void goTo(String url) {
        driver = new ChromeDriver();
        driver.get(url);
    }
    @And("I click Check Statistics")
    public void checkStatistics() {
        driver.findElement(By.cssSelector(".card:nth-child(1) button")).click();
        
    }
    @Then("I should be able to see a table")
    public void table() {
        assertNotNull(driver.findElement(By.id("continent")));
        assertNotNull(driver.findElement(By.id("country")));
        assertNotNull(driver.findElement(By.id("population")));
        assertNotNull(driver.findElement(By.id("cases")));
        assertNotNull(driver.findElement(By.id("deaths")));
        assertNotNull(driver.findElement(By.id("tests")));
        assertNotNull(driver.findElement(By.id("time")));
        assertNotNull(driver.findElement(By.id("day")));
    }

    @And("I insert country {string}")
    public void inputCountry(String country) {
        driver.findElement(By.id("countryName")).click();
        driver.findElement(By.id("countryName")).sendKeys(country);
    }

    @And("I click Search")
    public void clickSearch() {
        driver.findElement(By.id("searchButton")).click();
    }

    @Then("I should be able to see a table with data related to {string}")
    public void filteredTableByCountry(String country) {
        assertEquals("Country: "+country, driver.findElement(By.id("country")).getText());
    }


    @And("I click Check History")
    public void clickHistory() {
        driver.findElement(By.cssSelector(".card:nth-child(3) button")).click();
    }


    @And("I insert date {string}")
    public void insertDate(String date) {
        String[] dateSplitted = date.split("-", 3); //[yyyy, mm, ddd]
        //needs to be mmddyyyy
        String mmdddyyyyDate = dateSplitted[1]+dateSplitted[2]+dateSplitted[0];
        {
            WebElement element = driver.findElement(By.id("date"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        driver.findElement(By.id("date")).sendKeys(mmdddyyyyDate);
    }
    @Then("I should be able to see a table with data related to {string} from {string}")
    public void filteredTableByCountryAndDate(String country, String date) {
        assertEquals("Country: "+country, driver.findElement(By.id("country")).getText());
        assertEquals("Day: "+date, driver.findElement(By.id("day")).getText());
    }

    @After
    public void finish(){
        driver.close();
    }

}
