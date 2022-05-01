package ua.tqs.coviddata;
// Generated by Selenium IDE
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class CoviddataTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void coviddata() {
    driver.get("http://localhost:8083/");
    driver.findElement(By.cssSelector(".card:nth-child(1) button")).click();
    driver.findElement(By.id("countryName")).click();
    driver.findElement(By.id("countryName")).sendKeys("uk");
    driver.findElement(By.cssSelector(".col-md-3")).click();
    driver.findElement(By.id("searchButton")).click();
    driver.findElement(By.linkText("History")).click();
    driver.findElement(By.id("countryName")).click();
    driver.findElement(By.id("countryName")).sendKeys("uk");
    driver.findElement(By.id("date")).click();
    driver.findElement(By.id("date")).click();
    driver.findElement(By.id("date")).sendKeys("2022-02-06");
    driver.findElement(By.id("searchButton")).click();
    driver.close();
  }
}