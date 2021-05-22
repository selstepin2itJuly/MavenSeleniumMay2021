package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Reporter;
import org.testng.Reporter;

import testbase.TestBase;

public class Utility extends TestBase{

	/*
	 * scrollToElement()
	 * @param ele
	 * @param driver
	 */
	public static void scrolltoElement(WebElement ele)
	{
		//JavascriptExecutor je=(JavascriptExecutor) dr;
		((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView();", ele);//default value is true
	    ((JavascriptExecutor) dr).executeScript("window.scrollBy(0,-400)", "");
	}
	
	//Explicit wait time
	public static void waitforElement(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(dr, 15);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public static void waitforElementClickable(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(dr, 15);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public static void captureScreenshot() throws IOException
	{
		//TakesScreenshot ts= (TakesScreenshot) dr;
		//File file=ts.getScreenshotAs(OutputType.FILE);
		File file=((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(file, new File("./screenshots/"+getDate()+"_image.jpg"));

	}
	//for framework
	public static void attachScreenshot() throws IOException
	{
		//TakesScreenshot cs=(TakesScreenshot) dr;
		//String file = cs.getScreenshotAs(OutputType.BASE64);
		String file = ((TakesScreenshot) dr).getScreenshotAs(OutputType.BASE64);
		String st= "<img src=\"data:image/png;base64, " + file + "\" height=\"600\" width=\"800\" />";
		Reporter.log(st);
	}
	
	public static String getDate()
	{
		Date dt= new Date();
		System.out.println(dt);
		SimpleDateFormat sdf=new SimpleDateFormat("Y_MMM_dd_HH_mm_ss_S");
		String sDate = sdf.format(dt);
		System.out.println(sDate);
		return sDate;
		
	}
}
