import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    private By product1 = By.xpath("//div[@class=\"blockProductGrid__item qaBlockProductGrid__item \"][1]//img");
    private By product25 = By.xpath("//div[@class=\"blockProductGrid__item qaBlockProductGrid__item \"][25]//img");
    private By product50 = By.xpath("//div[@class=\"blockProductGrid__item qaBlockProductGrid__item \"][55]//img");
    private By product75 = By.xpath("//div[@class=\"blockProductGrid__item qaBlockProductGrid__item \"][75]//img");
    private By product99 = By.xpath("//div[@class=\"blockProductGrid__item qaBlockProductGrid__item \"][99]//img");

    Integer getCountAllProducts() {
        int countProduct = 1;
        try {
            while (true) {
                if (driver.findElement(By.xpath("//div[@class=\"blockProductGrid__item qaBlockProductGrid__item \"][" + countProduct + "]//img")).isDisplayed()) {
                    countProduct++;
                } else {
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            return countProduct;
        }
        return countProduct;
    }

    List<String> getImageUrl(List<String> list) {
        list.add(driver.findElement(product1).getAttribute("src"));
        list.add(driver.findElement(product25).getAttribute("src"));
        list.add(driver.findElement(product50).getAttribute("src"));
        list.add(driver.findElement(product75).getAttribute("src"));
        list.add(driver.findElement(product99).getAttribute("src"));
        return list;
    }

    private By productImageURL = By.xpath("//body/img");

    String getProductImageURL() {
        return driver.findElement(productImageURL).getAttribute("src");
    }
}
