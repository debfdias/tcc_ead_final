package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InscricoesPage extends GenericPage {
	
	public static final String URL = URL_BASE;// + Constantes.ADMIN_LISTA_URL;
	
	public static final String SELECT_STATUS_LOCATOR = "formAlterarStatusId:selectStatus";
	public static final By YES_CHANGE_STATUS_BUTTON_LOCATOR = By.id("formAlterarStatusId:simAlterarStatus");
	public static final By DIALOG_CHANGE_STATUS = By.id("dlgAlterarStatusId");
	
	public InscricoesPage(WebDriver driver) {
		super(driver);
	}

	public static By buttonChangeStatusLocator(int index) {
		return By.id("form:tabelaInscricoes:" + index + ":alterarStatusButton");
	}
	                                
	public static By statusTextLocator(int index) {
		return By.id("form:tabelaInscricoes:" + index + ":statusTable");
	}
	
	public void selectStatus(String value) {
		selectOne(SELECT_STATUS_LOCATOR, value);
	}
	
	public void changeStatus(int index, String value) {
		click(buttonChangeStatusLocator(index));
		waitElementDisplayed(DIALOG_CHANGE_STATUS);
		
		selectStatus(value);
		
		click(YES_CHANGE_STATUS_BUTTON_LOCATOR);
		
		waitElementDisplayed(GenericPage.INFO_SUMMARY_LOCATOR);
	}
}
