package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {

	private WebDriver dr;
	public Dashboard(WebDriver driver)
	{
		this.dr=driver;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(linkText="Logout")
	WebElement logout;
	
	@FindBy(xpath="//*[contains(text(),'Welcome to your store!')]")
	WebElement dashboardWelcomeMsg;
	
	@FindBy(xpath="//ul[@data-widget='treeview']/li/a/p")
	List<WebElement> menulist;
	
	
	public boolean isLoginSuccessful()
	{
		boolean b=false;
		try
		{
			b=logout.isDisplayed();
		}catch(Exception e) 
		{
			e.getStackTrace();
		}
		return b;
	}
	
	public boolean isWelcomeMsgDisplayedDashboard()
	{
		boolean b=false;
		try {
			b=dashboardWelcomeMsg.isDisplayed();
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return b;

	}

	public String[] getMenuList()
	{
		
		String[] st=new String[menulist.size()];
		int i=0;
		for(WebElement e:menulist)
		{
			st[i]=e.getText().trim();
			i++;
		}
		return st;
	}
	public int getMenuListCount()
	{
		
		return menulist.size();
	}
	
}

