package selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReservePage {
    private String url = "https://blazedemo.com/reserve.php";

    @FindBy(css = "tr:nth-child(2) .btn")
    private WebElement chooseFlight;

    public ReservePage(WebDriver driver){
        // this.driver = driver;
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    public void clickFlight(){
        chooseFlight.click();
    }    
}