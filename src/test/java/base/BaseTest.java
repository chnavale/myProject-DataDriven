package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import extentlisteners.ExtentListeners;
import utilities.DbManager;
import utilities.ExcelReader;
import utilities.MonitoringMail;

public class BaseTest {
	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\TestData.xlsx");
	public static Logger log = Logger.getLogger(BaseTest.class);
	public static MonitoringMail mail = new MonitoringMail();
	public static WebDriverWait wait;
	public static WebElement dropdown;
	public static WebElement hover;
	public static String browser;
	
	
	public void type(String locatorKey, String value) //types in an element
	{
		if (locatorKey.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locatorKey))).sendKeys(value);
			
			
		} else if (locatorKey.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locatorKey))).sendKeys(value);
			
			
		} else if (locatorKey.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locatorKey))).sendKeys(value);
			
		}
			log.info("Entered the value as \"" + value + "\" in " + locatorKey);
			ExtentListeners.test.info("Entered the value as \"" + value + "\" in " + locatorKey);
	}

	public void click(String locatorKey) //clicks on element
	{
		if (locatorKey.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locatorKey))).click();
			
			
		} else if (locatorKey.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locatorKey))).click();
			
			
		} else if (locatorKey.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locatorKey))).click();
			
			
		} else if (locatorKey.endsWith("_LINK")) {
			driver.findElement(By.linkText(OR.getProperty(locatorKey))).click();
			
		}
			log.info("Clicked on an element " + locatorKey);
			ExtentListeners.test.info("Clicked on an element " + locatorKey);
	}
	
	public boolean isElementPresent(String locatorKey) //checks presence of element
	{
	
	try {
		if (locatorKey.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locatorKey)));
			
			
		} else if (locatorKey.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locatorKey)));
			
			
		} else if (locatorKey.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
			
			
		} else if (locatorKey.endsWith("_LINK")) {
			driver.findElement(By.linkText(OR.getProperty(locatorKey)));
			
		}}catch(Throwable t) {
			
			
			log.info("Error while finding an element: " + locatorKey);
			ExtentListeners.test.info("Error while finding an element: " + locatorKey);
			
			return false;
			
		}
			log.info("Finding presence of an element: " + locatorKey);
			ExtentListeners.test.info("Finding presence of an element: " + locatorKey);
			return true;
	}
	
	public void select(String locatorKey, String value) //Selects from dropdown
	{
		if (locatorKey.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
			
			
		} else if (locatorKey.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locatorKey)));
			
			
		} else if (locatorKey.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
			
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
			log.info("Selecting an element " + locatorKey + " and slecting the value as " + value);
			ExtentListeners.test.info("Selecting an element " + locatorKey + " and slecting the value as " + value);
	}
		
	public void mouseHover(String locatorKey) {
		if(locatorKey.endsWith("_XPATH")) {
			hover = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_ID")) {
			hover = driver.findElement(By.id(OR.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_CSS")) {
			hover = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
		}
		Actions action=new Actions(driver);
		action.moveToElement(hover).perform();
	}
	

	@BeforeSuite
	public void setUp() {

		if (driver == null) {
			PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
			log.info("Test Execution Started!!!");

			try {
				fis = new FileInputStream("./src/test/resources/properties/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				config.load(fis);
				log.info("Config properties file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream("./src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				OR.load(fis);
				log.info("OR properties file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(System.getenv("browser") != null && !System.getenv("browser").isEmpty()){
				
				browser = System.getenv("browser");
			}else {
				
				browser = config.getProperty("browser");
			}
			
			config.setProperty("browser", browser);
			
			
			if(config.getProperty("browser").equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
				log.info("Chrome browser launched");
				
			}else if(config.getProperty("browser").equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				log.info("Firefox browser launched");
				
			}else if(config.getProperty("browser").equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
				log.info("Edge browser launched");
			}
				
			driver.get(config.getProperty("testsiteurl"));
			log.info("Navigated to " +config.getProperty("testsiteurl"));
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
			
			wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit.wait"))));
			
			try {
				DbManager.setMysqlDbConnection();
				log.info("Database connection established !!!");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@AfterSuite
	public void tearDown() {
		
		
		driver.quit();
		log.info("Test Execution Completed!!!");
	}

}
