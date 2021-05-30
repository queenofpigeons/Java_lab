package SystemTest;

import entities.*;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class SystemTest {
    String URL = "http://localhost:8080/";
    WebDriver driver;
    WebDriverWait wait;
    OrderService orderService = new OrderService();
    ClientService clientService = new ClientService();
    MovieService movieService = new MovieService();
    DiskService diskService = new DiskService();
    TypeService typeService = new TypeService();
    Clients client = new Clients("test", "12345", null);
    Movies movie = new Movies("Test mov", 2000, "Test dir", null);
    Orders order = new Orders(java.sql.Date.valueOf("2000-10-10"), java.sql.Date.valueOf("2000-10-10"), 0, false, clientService.findByIdClient(1), null);
    Types type = new Types("Test type", 1, null);
    Disks disk = new Disks(orderService.findByIdOrder(1), movieService.findByIdMovie(1), typeService.findByIdType(1));

    @BeforeClass
    public void settings() {
        final String ffDriver = "/usr/local/bin/geckodriver";
        System.setProperty("webdriver.gecko.driver", ffDriver);
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().window().setSize(new Dimension(1000, 1000));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Test()
    public void errorTest(){
        driver.get(URL+"/client?client_id=9999");
        Assert.assertEquals(driver.getTitle(), "Error");

        driver.get(URL+"/movie?film_id=9999");
        Assert.assertEquals(driver.getTitle(), "Error");

        driver.get(URL+"/type?film_id=9999");
        Assert.assertEquals(driver.getTitle(), "Error");

        driver.get(URL+"/order?film_id=9999");
        Assert.assertEquals(driver.getTitle(), "Error");

        driver.get(URL+"/disk?film_id=9999");
        Assert.assertEquals(driver.getTitle(), "Error");
    }

    @Test()
    public void checkClient() {
        driver.get(URL);
        Assert.assertEquals(driver.getTitle(), "Index");

        driver.findElement(By.id("clientList_link")).click();
        Assert.assertEquals(driver.getTitle(), "Client list");

        driver.findElement(By.id("clientAdd_button")).click();
        Assert.assertEquals(driver.getTitle(), "Add client");
        driver.findElement(By.id("client_name")).sendKeys(client.getClient_name());
        driver.findElement(By.id("phone")).sendKeys(client.getClient_phone());
        driver.findElement(By.id("submit_button")).click();

        Assert.assertEquals(driver.getTitle(), "Client page");
        String infoText = driver.findElement(By.id("clientInfo_text")).getText();
        Assert.assertTrue(infoText.contains(client.getClient_name()));
        Assert.assertTrue(infoText.contains(client.getClient_phone()));

        driver.findElement(By.id("delete_button")).click();
        Assert.assertEquals(driver.getTitle(), "Client list");
    }

    @Test()
    public void checkDisk() {
        driver.get(URL);
        Assert.assertEquals(driver.getTitle(), "Index");

        driver.findElement(By.id("disksList_link")).click();
        Assert.assertEquals(driver.getTitle(), "Disk list");

        driver.findElement(By.id("diskAdd_button")).click();
        Assert.assertEquals(driver.getTitle(), "Add disk");
        driver.findElement(By.id("disk_movie")).sendKeys(Integer.valueOf(disk.getMovie().getMovie_id()).toString());
        driver.findElement(By.id("disk_order")).sendKeys(Integer.valueOf(disk.getOrder().getOrder_id()).toString());
        driver.findElement(By.id("disk_type")).sendKeys(Integer.valueOf(disk.getType().getType_id()).toString());
        driver.findElement(By.id("submit_button")).click();

        Assert.assertEquals(driver.getTitle(), "Disk page");
        String infoText = driver.findElement(By.id("diskInfo_text")).getText();
        Assert.assertTrue(infoText.contains(Integer.valueOf(disk.getMovie().getMovie_id()).toString()));
        Assert.assertTrue(infoText.contains(Integer.valueOf(disk.getOrder().getOrder_id()).toString()));
        Assert.assertTrue(infoText.contains(Integer.valueOf(disk.getType().getType_id()).toString()));

        driver.findElement(By.id("delete_button")).click();
        Assert.assertEquals(driver.getTitle(), "Disk list");
    }

    @Test()
    public void checkMovie() {
        driver.get(URL);
        Assert.assertEquals(driver.getTitle(), "Index");

        driver.findElement(By.id("moviesList_link")).click();
        Assert.assertEquals(driver.getTitle(), "Movie list");

        driver.findElement(By.id("movieAdd_button")).click();
        Assert.assertEquals(driver.getTitle(), "Add movie");
        driver.findElement(By.id("movie_name")).sendKeys(movie.getMovie_name());
        driver.findElement(By.id("movie_director")).sendKeys(movie.getMovie_director());
        driver.findElement(By.id("movie_date")).sendKeys(movie.getMovie_date().toString());
        driver.findElement(By.id("submit_button")).click();

        Assert.assertEquals(driver.getTitle(), "Movie page");
        String infoText = driver.findElement(By.id("movieInfo_text")).getText();
        Assert.assertTrue(infoText.contains(movie.getMovie_name()));
        Assert.assertTrue(infoText.contains(movie.getMovie_director()));
        Assert.assertTrue(infoText.contains(movie.getMovie_date().toString()));

        driver.findElement(By.id("delete_button")).click();
        Assert.assertEquals(driver.getTitle(), "Movie list");
    }

    @Test()
    public void checkOrder() {
        driver.get(URL);
        Assert.assertEquals(driver.getTitle(), "Index");

        driver.findElement(By.id("ordersList_link")).click();
        Assert.assertEquals(driver.getTitle(), "Order list");

        driver.findElement(By.id("orderAdd_button")).click();
        Assert.assertEquals(driver.getTitle(), "Add order");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        driver.findElement(By.id("order_client")).sendKeys("1");
        driver.findElement(By.id("order_issued")).sendKeys(df.format(order.getOrder_issued()));
        driver.findElement(By.id("order_returned")).sendKeys(df.format(order.getOrder_returned()));
        driver.findElement(By.id("submit_button")).click();

        Assert.assertEquals(driver.getTitle(), "Order page");
        String infoText = driver.findElement(By.id("orderInfo_text")).getText();
        Assert.assertTrue(infoText.contains(Integer.valueOf(order.getClient().getClient_id()).toString()));
        Assert.assertTrue(infoText.contains(df.format(order.getOrder_issued())));
        Assert.assertTrue(infoText.contains(df.format(order.getOrder_returned())));
        Assert.assertTrue(infoText.contains(Float.valueOf(order.getOrder_cost()).toString()));
        Assert.assertTrue(infoText.contains(Boolean.valueOf(order.isOrder_is_paid()).toString()));

        driver.findElement(By.id("delete_button")).click();
        Assert.assertEquals(driver.getTitle(), "Order list");
    }

    @Test()
    public void checkType() {
        driver.get(URL);
        Assert.assertEquals(driver.getTitle(), "Index");

        driver.findElement(By.id("typesList_link")).click();
        Assert.assertEquals(driver.getTitle(), "Type list");

        driver.findElement(By.id("typeAdd_button")).click();
        Assert.assertEquals(driver.getTitle(), "Add type");
        driver.findElement(By.id("type_name")).sendKeys(type.getType_name());
        driver.findElement(By.id("type_cost")).clear();
        driver.findElement(By.id("type_cost")).sendKeys(Integer.valueOf(type.getType_cost()).toString());
        driver.findElement(By.id("submit_button")).click();

        Assert.assertEquals(driver.getTitle(), "Type page");
        String infoText = driver.findElement(By.id("typeInfo_text")).getText();
        Assert.assertTrue(infoText.contains(type.getType_name()));
        Assert.assertTrue(infoText.contains(Integer.valueOf(type.getType_cost()).toString()));

        driver.findElement(By.id("delete_button")).click();
        Assert.assertEquals(driver.getTitle(), "Type list");
    }

   /* @AfterClass
    public void clear() {
        driver.quit();
    }*/
}