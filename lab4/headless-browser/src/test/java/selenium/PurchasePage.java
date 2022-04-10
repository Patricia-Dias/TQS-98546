package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {
    private String url = "https://blazedemo.com/purchase.php";

    @FindBy(id = "inputName")
    private WebElement inputName;

    @FindBy(css = ".container:nth-child(2)")
    private WebElement container;

    @FindBy(className = "btn-primary")
    private WebElement purchaseFlightButton;

    public PurchasePage(WebDriver driver){
        // this.driver = driver;
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    public void insertInputName(String inp){
        inputName.sendKeys(inp);
    }

    //i dont know what this click is 0_0
    public void clickContainer(){
        container.click();
    }

    public void clickPurchaseFlight(){
        purchaseFlightButton.click();
    }
}
