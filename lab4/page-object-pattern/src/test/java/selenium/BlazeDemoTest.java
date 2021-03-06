// Generated by Selenium IDE
package selenium;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class BlazeDemoTest {
  private WebDriver driver;

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
  }

  @Test
  public void lab4_2() {
    
    //IndexPage
    IndexPage index = new IndexPage(driver);
    index.selectDepartureCity("Portland");
    index.selectDestinationCity("Berlin");
    index.clickFindFlights();

    //FlightsPage
    ReservePage reserve = new ReservePage(driver);
    reserve.clickFlight();


    //PurchasePage
    PurchasePage purchasePage = new PurchasePage(driver);
    purchasePage.insertInputName("zdxfcv");
    purchasePage.clickContainer();
    assertFalse(driver.findElement(By.cssSelector(".checkbox")).isSelected());
    assertThat(driver.findElement(By.cssSelector(".btn-primary")).getTagName(), is("input"));
    purchasePage.clickPurchaseFlight();

    //ConfirmationPage
    assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
    driver.close();
  }
}
