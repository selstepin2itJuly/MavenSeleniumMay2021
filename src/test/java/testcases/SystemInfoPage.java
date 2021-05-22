package testcases;

import org.testng.annotations.Test;

import pages.Dashboard;
import pages.LoginPage;
import pages.SystemInformationPage;
import testbase.TestBase;
import testbase.TestDataClass;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class SystemInfoPage {
	private WebDriver dr;
	LoginPage lp;
	Dashboard dp;
	SystemInformationPage sip;
	@Test
	public void verifySystemInfoList() {
		sip.gotToSystemInformation();
		sip.printLabelsAndValues();
		for(String s:sip.printLabelsAndValues())
		{
			Reporter.log(s);
		}
	}

	@BeforeMethod(alwaysRun=true)
	public void beforeMethod() throws IOException {
		 dr=TestBase.getInstance();
		  lp=new LoginPage(dr);
		  dp=new Dashboard(dr);
		  sip=new SystemInformationPage(dr);
		  lp.loginToApplication(TestDataClass.appUser, TestDataClass.appPassword);
	}

	@AfterMethod(alwaysRun=true)
	public void afterMethod() {
		dr.quit();
	}

}
