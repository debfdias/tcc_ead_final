package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends GenericPage {

	public static final String TITLE = "Sistema de Inscrições";
	public static final String URL = URL_BASE ;//+ Constantes.ADMIN_URL;

	public static final String CURSO_LOCATOR = "formEscolherCurso:curso";
	public static final By BUTTON_LOCATOR = By.id("formEscolherCurso:verInscricoes");
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public void selectCourse(String course) {
		selectOne(CURSO_LOCATOR, course);
	    
		click(BUTTON_LOCATOR);
		
		waitUrl(InscricoesPage.URL);
	}
}
