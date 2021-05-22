package testcases;

import org.testng.annotations.Test;

import pages.Dashboard;
import pages.LoginPage;
import testbase.TestBase;
import testbase.TestDataClass;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class TestCase2 {
	
	private WebDriver dr;
	LoginPage lp;
	Dashboard dp;
  @Test(priority=1, description="verify Welcome Msg On Login Page")
  public void verifyWelcomeMsgOnLoginPage() {
	  //lp.loginToApplication(TestDataClass.appUser,TestDataClass.appPassword);
	  Assert.assertTrue(lp.isDisplayWelcomeMsg());
	  Reporter.log(String.valueOf(lp.isDisplayWelcomeMsg()));
  }
  
  @Test(priority=2, description="verify Login is Successful")
  public void verifyLoginSuccessful() {
	  lp.loginToApplication(TestDataClass.appUser,TestDataClass.appPassword);
	  Assert.assertTrue(dp.isLoginSuccessful());
	  Assert.assertTrue(dp.isWelcomeMsgDisplayedDashboard());
	  Reporter.log(String.valueOf(dp.isLoginSuccessful()));
  }
  
  @Test(priority=3, description="verify Login Success With User Remember")
  public void verifyLoginSuccessWithUserRemember() {
	  lp.rememberUser();
	  lp.loginToApplication(TestDataClass.appUser,TestDataClass.appPassword);
	  Assert.assertTrue(dp.isLoginSuccessful());
  }
  
  @Test(priority=4, description="verify Menu Count on Dashboard", 
		  dependsOnMethods="verifyMenuListDashbaord", enabled=true)
  public void verifyMenuCountDashboard() {
	  lp.loginToApplication(TestDataClass.appUser,TestDataClass.appPassword);
	  Assert.assertTrue(dp.isLoginSuccessful());
	  int i=dp.getMenuListCount();
	  Assert.assertEquals(i, 11);
  }
  
  @Test(priority=5, description="verify Menu List Dashbaord", enabled=true)
  public void verifyMenuListDashbaord() {
	  String[] exp= {"Dashboard", "Catalog", "Sales",
			   "Customers", "Promotions", "Content management",
			   "Configuration", "System", "Reports", "Help"};
	  lp.loginToApplication(TestDataClass.appUser,TestDataClass.appPassword);
	  Assert.assertTrue(dp.isLoginSuccessful());
	  String[] str=dp.getMenuList();
	  Assert.assertEquals(str, exp);
	  //Assert.assertTrue(false);
  }
  
  @BeforeMethod(alwaysRun=true) //pre-requisite
  public void beforeMethod() throws IOException {
	  dr=TestBase.getInstance();
	  lp=new LoginPage(dr);
	  dp=new Dashboard(dr);
	  
  }

  @AfterMethod(alwaysRun=true) //post-requisite
  public void afterMethod() {
	  //quit the browser
	  dr.quit();
  }

}
