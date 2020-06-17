package objectRepositoryPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pageObject {
	WebDriver driver;
	
    /**

     * All WebElements are identified by @FindBy annotation

     */

	@FindBy(className="next-head")
	WebElement titleText;
	
	@FindBy(css = ".InputBar__SearchInput-t6v2m1-1")
	WebElement searchInputHome;
	
	@FindBy(css = ".InputBar__SearchButton-t6v2m1-2")
	WebElement searchIcon;
	
	@FindBy(name = "categoriesAggregation")
	WebElement filtreList;
	
	@FindBy(name = "viewAllBrands")
	WebElement brandViewAll;
	
	@FindBy(id = "apply")
	WebElement applyFilter;
	
	public pageObject(WebDriver driver) {
		this.driver = driver;
		
		//This initElements method wil create all WebElements
		
		PageFactory.initElements(driver, this);
	}
	
	public void setParamSearch(String inputToSearch) {
			searchInputHome.sendKeys(inputToSearch);
	}
	
	public void clickSearchIcon() {
		searchIcon.click(); 
	}

	
	/**
	 * This POM method will be exposed in test case to search into the web Page
	 * @param inputToSearch
	 * @param filtreByProductCharacteristic
	 * @param filtreByBrand
	 * @return
	 */
	
	public void searchProductAndVerify(String inputToSearch, String filtreByProductCharacteristic, String filtreByBrand) {
		Actions actions = new Actions(driver);
		
		String xpathFindCharacteristic = "//label[contains(.,'" + filtreByProductCharacteristic+" ')]\"";
		
		//Fill input to Search
		this.setParamSearch(inputToSearch);
			
		//Click in Search Login
		this.clickSearchIcon();
		
		//Searching in the filtres to click into the Input To search
		WebElement subNode = new WebDriverWait(driver,10).until(
			    ExpectedConditions.presenceOfNestedElementLocatedBy(filtreList, By.partialLinkText(inputToSearch)));
		subNode.click();

		//Find element by link text and store in variable "Element"
        WebElement Element = driver.findElement(By.xpath("//label[contains(.,'Heladeras con freezer ')]"));
		actions.moveToElement(Element);
		actions.perform();
		
		WebElement Characteristic = new WebDriverWait(driver,10).until(
			    ExpectedConditions.presenceOfNestedElementLocatedBy(filtreList, By.xpath("//label[contains(.,'Heladeras con freezer ')]")));
		Characteristic.click();
		
		actions.moveToElement(brandViewAll);
		actions.perform();
		brandViewAll.click();
		
		//Searching in the filtres to click into the brand searching
		WebElement brandClick = new WebDriverWait(driver,10).until(
			    ExpectedConditions.presenceOfNestedElementLocatedBy(filtreList, By.xpath("//label[contains(.,'Samsung ')]")));
		
		//Apply the filter brand name
		brandClick.click();
		
		//Button to Apply the filter by brand
		applyFilter.click();
	}
	
}
