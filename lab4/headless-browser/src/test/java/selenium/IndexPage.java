package selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class IndexPage {
    // private WebDriver driver;
    private String url = "https://blazedemo.com/";

    @FindBy(name = "fromPort")
    private WebElement fromPortDropdown;

    @FindBy(name = "toPort")
    private WebElement toPortDropdown;

    @FindBy(className = "btn-primary")
    private WebElement findFightsButton;

    public IndexPage(WebDriver driver){
        // this.driver = driver;
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    public void selectDepartureCity(String departure){
        Select city = new Select(fromPortDropdown);
        city.selectByVisibleText(departure);
    }

    public void selectDestinationCity(String destination){
        Select city = new Select(toPortDropdown);
        city.selectByVisibleText(destination);
    }

    public void clickFindFlights(){
        findFightsButton.click();
    }    
    
}
