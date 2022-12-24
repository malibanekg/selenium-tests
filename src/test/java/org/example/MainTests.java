package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import java.util.List;

public class MainTestShort {

    private static Logger logger = LoggerFactory.getLogger(Logger.class);

    WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
//        System.setProperty("webdriver.chrome.driver", "C:/Users/djordje/Desktop/chromedriver.exe");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
    }

    @Test
    public void PolovniTest1() throws InterruptedException {
        // static dropdown
        driver.get("https://www.polovniautomobili.com/");
//      close popups
//        first popup
//        driver.findElement(By.cssSelector(".fc-button.fc-cta-consent.fc-primary-button")).click();
//        driver.findElement(By.xpath("//button[@class='fc-button fc-cta-consent fc-primary-button']")).click();

//        second popup
        driver.findElement(By.xpath("//div[@class='_ado-responsiveFooterBillboard-hover']")).click();
//        third popup
        driver.findElement(By.xpath("//div[contains(@class, 'uk-width-medium-1-10') and contains(@class, 'uk-width-1-1')]")).click();

        driver.manage().window().maximize();
//        Select s = new Select(driver.findElement(By.xpath("#brand")));

//        THE FIRST SOLUTION *****
        driver.findElement(By.xpath("//div[contains(@class, 'SumoSelect') and contains(@class, 'sumo_brand')]")).click();
//        driver.findElement(By.xpath(
//                "//div[@class='SumoSelect sumo_brand']/p[@class='CaptionCont SelectBox search']/span")).click();

        List<WebElement> div_list = driver.findElements(By.xpath("//div[@class='optWrapper']"));
        WebElement div_brand = div_list.get(0);

//        get all li elements inside selected div
        List<WebElement> li_list = div_brand.findElements(By.xpath("//ul/li"));

//        search list for a specific text
        for (WebElement option : li_list) {
            if (option.getText().equalsIgnoreCase("Opel")) {
                option.click();
                break;
            }
        }
    }

    @Test
    public void PolovniTest2() throws InterruptedException {

        driver.get("https://www.polovniautomobili.com/");
//      close popups
//        first popup
//        driver.findElement(By.cssSelector(".fc-button.fc-cta-consent.fc-primary-button")).click();
//        driver.findElement(By.xpath("//button[@class='fc-button fc-cta-consent fc-primary-button']")).click();

//        second popup
        driver.findElement(By.xpath("//div[@class='_ado-responsiveFooterBillboard-hover']")).click();
//        third popup
        driver.findElement(By.xpath("//div[contains(@class, 'uk-width-medium-1-10') and contains(@class, 'uk-width-1-1')]")).click();

        driver.manage().window().maximize();

//        get car value
        driver.findElement(By.xpath("//div[contains(@class, 'SumoSelect') and contains(@class, 'sumo_brand')]")).click();

        List<WebElement> labels = driver.findElements(By.cssSelector("div[class='optWrapper'] ul li"));

        for(WebElement option :labels){
            if(option.getText().equalsIgnoreCase("Opel")){
                option.click();
                break;
            }
        }
    }

    @Test
    public void PolovniTest3() throws InterruptedException {
        // Solution provided by Srđan Todorović
        driver.manage().window().maximize();
        driver.get("https://www.polovniautomobili.com");

        // ugasi pop-ove
//        driver.findElement(By.xpath("//button[@class='fc-button fc-cta-consent fc-primary-button']")).click();
        driver.findElement(By.xpath("//div[@class='_ado-responsiveFooterBillboard-hover']")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'uk-width-medium-1-10') and contains(@class, 'uk-width-1-1')]")).click();

        // izbor marke
        driver.findElement(By.xpath(
                "//div[@class='SumoSelect sumo_brand']/p[@class='CaptionCont SelectBox search']/span")).click();

        driver.findElement(By.xpath(
                "//div[@class='SumoSelect sumo_brand open']//input")).sendKeys("Opel");

        driver.findElement(By.xpath(
                        "//div[@class='SumoSelect sumo_brand open']//ul[@class='options']/li[@class='opt']"))
                .click();

        // cekiraj garaniciju
        driver.findElement(By.xpath("//input[@id='warranty']")).click();

        // unos max cene
        driver.findElement(By.xpath("//input[@id='price_to']")).sendKeys("2000");

        // pretraga
        driver.findElement(By.xpath("//button[@class='js-search-buttons paOrangeButtonPrimary uk-width-1-1 uk-margin-small-bottom search-image']")).click();

        driver.findElement(By.xpath("//div[@class='_ado-responsiveFooterBillboard-hover']")).click();

        // uzmi jedan element
        WebElement oglas = driver.findElements(By.xpath("//article")).get(1);
        System.out.println(oglas.getText());
        oglas.findElement(By.xpath("//a[@class='ga-title']")).click();

        // driver.quit();
    }

    @Test
    public void testESkyInitial() throws InterruptedException {
        // static dropdown
        driver.get("https://www.esky.rs/");
        Thread.sleep(1000L);
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//div[@data-content-id='pax-counter']")).click();

        for(int i=1; i<5; i++)
        {
            driver.findElement(By.xpath("//div[@class='pax adult '] //a[@data-operator='plus']")).click();
        }

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='pax-number adult-pax-amount']")).getText(), "5");
//        driver.findElement(By.xpath("//a[@class='btn small function ghost close-pax-counter']")).click();
        driver.findElement(By.cssSelector(".btn.small.function.ghost.close-pax-counter")).click();
    }

    @Test
    public void testESky() throws InterruptedException {
        // Solution provided by Miodrag Ranđelović
        driver.manage().window().maximize();
        driver.get("https://www.esky.rs");

        // Uneti u polazak nis
        Thread.sleep(1000L);
        driver.findElement(By.xpath("//input[@id='departureRoundtrip0']")).sendKeys("Nis");

        // Kliknuti na Konstantin
        Thread.sleep(4000L);
        driver.findElement(By.xpath("//a[@data-city-with-country='Niš Srbija']")).click();

        // Kliknuti na na arrival
        Thread.sleep(2000L);
        driver.findElement(By.xpath("//input[@id='arrivalRoundtrip0']")).sendKeys("Beograd, Nikola Tesla, Srbija (BEG)");

        // kliknuti na datum polaska
        Thread.sleep(1000L);
        driver.findElement(By.xpath("//input[@id='departureDateRoundtrip0']")).click();

        // Prebaciti kalendar da prikazuje dane u decembru
        Thread.sleep(1000L);
        driver.findElement(By.xpath("//a[@class='ui-datepicker-next ui-corner-all']")).click();

        // izabrati 16. decembar
        Thread.sleep(1000L);
        driver.findElement(By.linkText("6")).click();
        //driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[5]/a")).click();

        // kliknuti na datum povratka
        Thread.sleep(1000L);
        driver.findElement(By.xpath("//input[@id='departureDateRoundtrip1']")).click();

        // Prebaciti kalendar da prikazuje dane u januaru
        Thread.sleep(1000L);
        driver.findElement(By.xpath("//a[@class='ui-datepicker-next ui-corner-all']")).click();

        // izabrati 13. januar
        Thread.sleep(1000L);
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[5]/a")).click();

        driver.findElement(By.xpath("//div[@data-content-id='pax-counter']")).click();

        for(int i=1; i<5; i++)
        {
            driver.findElement(By.xpath("//div[@class='pax adult '] //a[@data-operator='plus']")).click();
        }

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='pax-number adult-pax-amount']")).getText(), "5");
//        driver.findElement(By.xpath("//a[@class='btn small function ghost close-pax-counter']")).click();
        driver.findElement(By.cssSelector(".btn.small.function.ghost.close-pax-counter")).click();

        Thread.sleep(1000L);
        driver.findElement(By.xpath("//*[@id='multiQsfFlights']/form/section[2]/div[2]/fieldset[2]/button")).click();
    }

//    @AfterMethod
//    public void afterMethod()
//    {
////        driver.close();
//    }
}
