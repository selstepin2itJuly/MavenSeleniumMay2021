package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SystemInformationPage {

	private WebDriver dr;
	public SystemInformationPage(WebDriver driver)
	{
		this.dr=driver;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(xpath="//ul[@data-widget='treeview']/li/a/p[contains(text(),'System')]")
	WebElement system;
	
	@FindBy(xpath="//ul[@class='nav nav-treeview']/li/a/p[contains(text(),' System information')]")
	WebElement sysInfo;
	
	@FindBy(xpath="//div[@class='label-wrapper']/parent::*/parent::*/descendant::*[@class='form-text-row']")
	List<WebElement> values;
	
	@FindBy(xpath="//div[@class='label-wrapper']/label")
	List<WebElement> label;
	
	public void gotToSystemInformation()
	{
		system.click();
		sysInfo.click();
	}
	
	public String[] printLabelsAndValues()
	{
		String[] exp=new String[label.size()];
		int i=0;
		for(WebElement e:label)
		{
			System.out.println(e.getText()+":"+values.get(i).getText());
			exp[i]=e.getText()+":"+values.get(i).getText();
			i++;
		}
		return exp;
	}
	
}
