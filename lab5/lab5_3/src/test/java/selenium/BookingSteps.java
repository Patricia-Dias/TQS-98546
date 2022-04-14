// Generated by Selenium IDE
package selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BookingSteps {
  private WebDriver driver;

  @When("I navigate to {string}")
  public void iNavigateTo(String url) {
      driver = WebDriverManager.chromedriver().create();
      driver.get(url);
  }

  @And("I select a flight from {string} to {string}")
  public void selectFlightCities(String from, String to) {
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath(String.format("//option[. = %s]", from))).click();
    }
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath(String.format("//option[. = %s]", to))).click();
    }
    driver.findElement(By.cssSelector(".container:nth-child(3)")).click();
    
  }

  @And("I click Find Flights")
  public void pressFindFlights() {
    driver.findElement(By.cssSelector(".btn-primary")).click();
  }

  @And("I click Choose Flight")
  public void pressChooseFlight() {
    driver.findElement(By.cssSelector("tr:nth-child(2) .btn")).click();
  }

  @And("I write {string} in {string}")
  public void writeOnInputBox(String input, String box){
    driver.findElement(By.id(box)).click();
    driver.findElement(By.id(box)).sendKeys(input);
  }

  @And("I click Purchase Flights")
  public void pressPurchaseFlight(){
    driver.findElement(By.cssSelector(".btn-primary")).click();
  }

  @Then("I should be able to see the receipt {string}")
  public void seeReceipt(String confirmation){
    assertEquals(driver.getTitle(), confirmation);
  }

  @After
  public void finish(){
    driver.close();
  }

    
}
