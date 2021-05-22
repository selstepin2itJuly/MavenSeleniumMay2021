package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver dr;

	public LoginPage(WebDriver driver) {
		this.dr = driver;
		PageFactory.initElements(dr, this);// important to initialize
	}

	@FindBy(id = "Email")
	private WebElement email;

	@FindBy(id = "Password")
	private WebElement password;

	@FindBy(id = "RememberMe")
	private WebElement rememeber;

	@FindBy(css = "[type='submit']")
	private WebElement login;

	@FindBy(xpath = "//div[@class='title']/strong")
	private WebElement msg;

	public void loginToApplication(String user, String pass) {
		email.clear();
		email.sendKeys(user);
		password.clear();
		password.sendKeys(pass);
		login.click();
	}

	public String readWelcomeMsg() {
		return msg.getText();
	}

	public void rememberUser() {
		if (rememeber.isSelected() == false) {
			rememeber.click();
		}
	}

	public boolean isDisplayWelcomeMsg() {
		boolean b = false;
		try {
			b = msg.isDisplayed();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return b;

	}
}
