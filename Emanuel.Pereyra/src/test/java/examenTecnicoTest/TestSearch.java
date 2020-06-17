package examenTecnicoTest;

import org.testng.annotations.Test;

import objectRepositoryPage.pageObject;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;


public class TestSearch {
	WebDriver driver;
	
  @Test
  public void SearchingProduct() {
	  //Create PageObject object
	  pageObject objPage = new pageObject(driver);
	  
	  //Assert of Title Page
	  assertTrue(driver.getTitle().contains("Frávega: Electrodomésticos, Tecnología y Artículos para el hogar"));
	  
	  //Search product
	  objPage.searchProductAndVerify("Heladera", "Heladeras con Freezer", "Samsung");
  }
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", "src\\resource\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get("https://www.fravega.com/");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @AfterTest
  public void afterTest() {
	  //driver.close();
  }

}
