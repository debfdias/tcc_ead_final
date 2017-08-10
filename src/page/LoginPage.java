
package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends GenericPage {

	public static final By USERNAME_LOCATOR = By.id("form:email");
	public static final By PASSWORD_LOCATOR = By.id("formLogin:senha");
	public static final By LOGIN_BUTTON_LOCATOR = By.id("formLogin:loginButton");
	
	public static final String TITLE = "Sistema de Inscrições - Login";
	
	public static final String URL = URL_BASE + "authentication/login.xhtml";// + Constantes.LOGIN_ADMIN_URL;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void goToPage() {
		driver.get(URL);
	}
	
	public void loginAs(String username, String password) {
		login(username, password);
		
		waitUrl(HomePage.URL);
	}
	
	public void loginAsExpectingError(String username, String password) {
		login(username, password);
		
		waitElementDisplayed(GenericPage.ERROR_SUMMARY_LOCATOR);
	}
	
	private void login(String username, String password) {
		type(USERNAME_LOCATOR, username);
		type(PASSWORD_LOCATOR, password);
		
		click(LoginPage.LOGIN_BUTTON_LOCATOR);
	}
	
	public void inputLogin(String username) {
		type(USERNAME_LOCATOR, username);
	}
	
	public void inputPassword(String password) {
		type(PASSWORD_LOCATOR, password);
	}
	
	public void clickJoinButton() {
		click(LoginPage.LOGIN_BUTTON_LOCATOR);
	}
}
