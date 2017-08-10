package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

	
public class GenericPage {
	protected final WebDriver driver;
	
	public static final By INFO_SUMMARY_LOCATOR = By.className("ui-messages-info-summary");
	public static final By INFO_DETAILS_LOCATOR = By.className("ui-messages-info-detail");
	
	public static final By ERROR_SUMMARY_LOCATOR = By.className("ui-messages-error-summary");
	public static final By ERROR_DETAILS_LOCATOR = By.className("ui-messages-error-detail");
	
	public static final String URL_BASE = "http://localhost:8080/SistemaTCC/";

	public GenericPage(WebDriver driver) {
		this.driver = driver;
	}

	public void type(By locator, String value) {
		driver.findElement(locator).sendKeys(value);
	}

	public void submit(By locator) {
		driver.findElement(locator).submit();
	}
	
	public void click(By locator) {
		driver.findElement(locator).click();
	}
	
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}
	
	public boolean isDisplayed(By locator) {
		return driver.findElement(locator).isDisplayed();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void waitUrl(final String url) {
		(new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		        return d.getCurrentUrl().contains(url);
		    }
		});
	}
	
	public void waitElementDisplayed(final By locator) {
		(new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		        return d.findElement(locator).isDisplayed();
		    }
		});
	}
	
	public void selectOne(String idPrefix, String value) {
		if (StringUtils.isNotBlank(value)) {
			WebElement element = driver.findElement(By.id(idPrefix + "_label"));
			element.click();
			driver.findElement(By.xpath("//div[@id='" + idPrefix + "_panel']/div/ul/li[text()='" + value + "']")).click();
		}
	}
}
