package testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.exec.OS;
import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;

public class TestBase {

	public static Logger log=Logger.getLogger(TestBase.class);
	public static WebDriver dr;
	public static Properties prop;
	public static Properties getTestData;
	public static String browser;
	
	public static WebDriver getInstance() throws IOException
	{
		FileInputStream inStream=new FileInputStream("./src/main/resources/config/setting.properties");
		FileInputStream inTestData=new FileInputStream("./src/test/resources/testdata/testdata.properties");
		prop=new Properties();
		getTestData=new Properties();
		prop.load(inStream);
		getTestData.load(inTestData);
		browser=prop.getProperty("browser");
		if(OS.isFamilyWindows()) 
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", prop.getProperty("agent_chrome"));
				
				ChromeOptions opt=new ChromeOptions();
				opt.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				opt.addArguments("start-maximized");
				opt.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
				//LoggingPreferences logPrefs = new LoggingPreferences();
				//logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
				//opt.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
				dr=new ChromeDriver(opt);
			}else if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", prop.getProperty("agent_firefox"));
				dr=new FirefoxDriver();
			}else if(browser.equalsIgnoreCase("ie")) 
			{
				System.setProperty("webdriver.ie.driver", prop.getProperty("agent_ie"));
				dr=new InternetExplorerDriver();
			}else if (browser.equalsIgnoreCase("edge"))
			{
				System.setProperty("webdriver.edge.driver", prop.getProperty("agent_edge"));
				dr=new EdgeDriver();
			}else
			{
				Throwable thr=new Throwable();
				thr.initCause(null);
			}
		}else if(OS.isFamilyMac())
		{
			System.setProperty("webdriver.safari.driver", prop.getProperty("agent_safari"));
			dr=new SafariDriver();
		}
			
		
		dr.manage().deleteAllCookies();
		Set<Cookie> sr = dr.manage().getCookies();
		System.out.println(sr);
		dr.manage().deleteAllCookies();
		Set<Cookie> st = dr.manage().getCookies();
		System.out.println(st);
		if(OS.isFamilyWindows())
		{
			dr.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		//dr.manage().window().maximize();
		//dr.get("https://demoqa.com/automation-practice-form");
		dr.get(prop.getProperty("url"));
		return dr;
	}
	
}
