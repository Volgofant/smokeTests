import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class MainPage {
    private WebDriver driver;

    MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private By missionText = By.xpath("//h2[@class=\"home__mission__heading\"]");
    String getMissionText() {
        String text = driver.findElement(missionText).getText();
        return text;
    }

    private By mainBanner = By.xpath("//div[@class=\"homeSlider\"]//img");
    private By secondBanner = By.xpath("//a[@class=\"item js-gtm-track-landing\"][1]//img");
    private By thirdBanner = By.xpath("//a[@class=\"item js-gtm-track-landing\"][2]//img");
    private By banner4 = By.xpath("//div[@class=\"item\"]//a[@class=\"item js-gtm-track-landing\"][1]/img");
    private By banner5 = By.xpath("//div[@class=\"item\"]//a[@class=\"item js-gtm-track-landing\"][2]/img");
    private By banner6 = By.xpath("//div[@class=\"home__cta__three-one\"]//a[@class=\"item js-gtm-track-landing\"][1]/img");
    private By banner7 = By.xpath("//div[@class=\"home__cta__three-one\"]//a[@class=\"item move-up js-gtm-track-landing\"]/img");

    List<String> getBannerUrl(List<String> list) {
        list.add(driver.findElement(mainBanner).getAttribute("src"));
        list.add(driver.findElement(secondBanner).getAttribute("src"));
        list.add(driver.findElement(thirdBanner).getAttribute("src"));
        list.add(driver.findElement(banner4).getAttribute("src"));
        list.add(driver.findElement(banner5).getAttribute("src"));
        list.add(driver.findElement(banner6).getAttribute("src"));
        list.add(driver.findElement(banner7).getAttribute("src"));
        return list;
    }
    private By bannerImageURL = By.xpath("//body/img");
    String getBannerImageURL() {
        return driver.findElement(bannerImageURL).getAttribute("src");
    }

    List<String> getCategoriesName(List<String> list) {
        for(int i = 1; i < 8; i ++) {
            list.add(driver.findElement(By.xpath("//ul[@class=\"l-header__nav-items jsNavigation\"]/li[@class=\"jsMainMenuItem l-header__nav-item\"][" + i + "]/a")).getText());
        }
        return list;
    }
    private By categories = By.xpath("ul[@class=\"l-header__nav-items jsNavigation\"]");
}
