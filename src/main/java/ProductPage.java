import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class ProductPage {
    private WebDriver driver;
    ProductPage(WebDriver driver) {
        this.driver = driver;
    }
    By productName = By.xpath("//h1[@class=\"blockProductHeading__heading\"]");
    private By productImage = By.xpath("//img[@class=\"blockProductImage__preview jsProductImage__preview\"]");
    private By controlProductImage = By.xpath("//body/img");

    String getProductImageUrl() {
        return driver.findElement(productImage).getAttribute("src");
    }
    By randomProduct;
    By choiceRandomProduct(int x) {
        return randomProduct = By.xpath("//div[@class=\"blockProductGrid__item qaBlockProductGrid__item \"]["+ x +"]");
    }
    String checkControlProductImage() {
        return driver.findElement(controlProductImage).getAttribute("src");
    }

    Boolean checkVisibleName() {
        return driver.findElement(productName).isDisplayed();
    }

}
