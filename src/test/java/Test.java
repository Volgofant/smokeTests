import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {
    private WebDriver driver;
    private MainPage mainPage;
    private ProductPage productPage;
    private SearchPage searchPage;
    private int x;

    @BeforeClass
    public void setStart() {
        WebDriverManager.chromedriver().version("75.0.3770.140").setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--proxy-server='direct://'");
        options.addArguments("--proxy-bypass-list=*");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @org.testng.annotations.Test
    public void mainPageShouldHaveMissionText() {
        mainPage = new MainPage(driver);
        driver.get("https://shop.westwing.ru/");
        Assert.assertEquals("Вдохновлять и делать каждый дом красивым!", mainPage.getMissionText());
    }

    @org.testng.annotations.Test
    public void mainPageShouldHaveCategories() {
        mainPage = new MainPage(driver);
        driver.get("https://shop.westwing.ru/");
        List<String> list = new ArrayList<>();
        mainPage.getCategoriesName(list);
        boolean cats = true;
        for (String s : list) {
            cats = (s.equals("Мебель") || s.equals("Декор и интерьерное хранение") || s.equals("Освещение") || s.equals("Текстиль и ковры") || s.equals("Посуда и аксессуары для кухни") || s.equals("Новый год и Рождество") || s.equals("Подарки"));
        }
        Assert.assertTrue(cats);
    }

    @org.testng.annotations.Test
    public void mainBannerVisible() throws InterruptedException {
        mainPage = new MainPage(driver);
        driver.get("https://shop.westwing.ru/");
        List<String> list = new ArrayList<>();
        mainPage.getBannerUrl(list);
        for (String s : list) {
            driver.get(s);
            Assert.assertEquals(s, mainPage.getBannerImageURL());
        }
    }

    @org.testng.annotations.Test
    public void searchPageShouldHave99Products() {
        searchPage = new SearchPage(driver);
        driver.get("https://shop.westwing.ru/all-products/?q=");
        Assert.assertEquals(java.util.Optional.of(100), java.util.Optional.ofNullable(searchPage.getCountAllProducts()));
    }

    @org.testng.annotations.Test
    public void searchPageShouldHaveVisibleImage() {
        searchPage = new SearchPage(driver);
        driver.get("https://shop.westwing.ru/all-products/?q=");
        List<String> list = new ArrayList<>();
        searchPage.getImageUrl(list);
        for (String s : list) {
            driver.get(s);
            Assert.assertEquals(s, searchPage.getProductImageURL());
        }
    }

    @org.testng.annotations.Test
    public void productPageFullCheck() {
        productPage = new ProductPage(driver);
        driver.get("https://shop.westwing.ru/all-products/?q=");
        productPage.choiceRandomProduct((int) (Math.random() * 99) + 1);
        driver.findElement(productPage.randomProduct).click();
        Assert.assertEquals(java.util.Optional.of(true), java.util.Optional.ofNullable(productPage.checkVisibleName()));
        String checkPoint = productPage.getProductImageUrl();
        driver.get(productPage.getProductImageUrl());
        Assert.assertEquals(checkPoint, productPage.checkControlProductImage());
    }

    @org.testng.annotations.Test
    public void checkLogin() {
        mainPage = new MainPage(driver);
        driver.get("https://shop.westwing.ru/customer/account/login/");
        driver.findElement(By.xpath("//input[@id=\"LoginForm_email\"]")).sendKeys("shopTestLogin@club.ru");
        driver.findElement(By.xpath("//input[@id=\"LoginForm_password\"]")).sendKeys("westwingpas");
        driver.findElement(By.xpath("//button[text()='Войти']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class=\"l-header__bottom-item-subline\"]")).getText(), "Тест");
    }

    @AfterClass
    public void setDown() {
        driver.quit();
    }
}
