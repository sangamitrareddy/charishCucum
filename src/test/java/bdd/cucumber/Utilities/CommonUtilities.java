package bdd.cucumber.Utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CommonUtilities{
	
	public static WebDriver driver;
	public static Properties properties;
	public static ExtentReportingUtil extentReport=new ExtentReportingUtil();
	public static String mini, medium, max;
	public static int minimumWait,mediumWait,maximumWait;
		
		String cwd = System.getProperty("user.dir");
				
		public CommonUtilities() {						
			try{
				properties = new Properties();
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\Properties\\config.properties");		
				properties.load(ip);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}			
		    }
		
		static {
			
			mini = properties.getProperty("minimumWait");
			minimumWait = Integer.parseInt(mini);		
			medium = properties.getProperty("mediumWait");
			mediumWait = Integer.parseInt(medium);
			max = properties.getProperty("maximumWait");
			maximumWait = Integer.parseInt(max);
			
		}
				
		public void openBrowser() throws InterruptedException {
			
			String browser = properties.getProperty("browserName");
			String url = properties.getProperty("url");
			
			try {				
				if (browser.equalsIgnoreCase("chrome")) {					
					System.setProperty("webdriver.chrome.driver", cwd+"/drivers/chromedriver.exe");
					driver = new ChromeDriver();					
				} else if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", cwd+"/drivers/geckodriver.exe");
					driver = new FirefoxDriver();
				} 				
			} catch (Exception e) {				
				ExtentReportingUtil.createLogForFail("Unable to open the browser"+browser+" .Error is "+e);				
			}
			waitUtil(maximumWait);
			try {
				driver.get(url);
				driver.manage().window().maximize();
				Thread.sleep(10000);
				ExtentReportingUtil.createLogForpass("Application launched successfully");
				
			} catch (Exception e) {
				ExtentReportingUtil.createLogForFail("Unable to launch application "+url);
			}
			}
				
		public static String getScreenShotAs(){
			TakesScreenshot ts=(TakesScreenshot) driver;
			File src= ts.getScreenshotAs(OutputType.FILE);
		 String path= System.getProperty("user.dir")+"\\target\\"+System.currentTimeMillis()+".png";
		 File destination=new File(path);
		 try {
						FileUtils.copyFile(src,destination);
					} catch (IOException e) {
						
						System.out.println("Captured Failed "+e.getMessage());
					}			
			return path;		
		}
		
		/************** selenium generic utilities*******************/
		
		public String getPageTitle() {
			String pageTitle = driver.getTitle();
			System.out.println(pageTitle);
			return pageTitle;		
		}
		public void verifyPage(String str) {
			try {
			if (!getPageTitle().contains(str)) {
				Assert.fail("specified page not loaded");}
			}catch(Exception e) {
			}
		}	
		
		public boolean isDisplayElement(By locator, String elementName) {
			boolean isDisplayedMenu = false;
			try {
				waitThread(mediumWait);
				isDisplayedMenu = driver.findElement(locator).isDisplayed();
				return isDisplayedMenu;
			} catch (Exception e) {				
				ExtentReportingUtil.createLogForFail("Unable to identify the "+elementName);
			}
			return isDisplayedMenu;	
		}
		
		public List<WebElement> getAllElementsInPage(By locator, String elementName){
			
			List<WebElement> menuItems = null;
			try {
				return menuItems = driver.findElements(locator);
			} catch (Exception e) {
				ExtentReportingUtil.createLogForFail("Unable to identify the "+elementName+" in page");
			}
			return menuItems;
		}
		
		public void clickElement(By locator, String elementName) {
			
			try {
				driver.findElement(locator).click();
				waitThread(minimumWait);
				ExtentReportingUtil.createLogForpass("Click on "+elementName+" is successful");
			} catch (Exception e) {
				ExtentReportingUtil.createLogForFail("Unable to click on "+elementName);
			}
			
			
		}
		
		public void moveToElement(By locator, String elementName) {
			
			try {
				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(locator)).build().perform();
			} catch (Exception e) {
				ExtentReportingUtil.createLogForFail("Unable to move to "+elementName);
			}
			
			
		}
		
		public List<WebElement> getDropdownOptions(By locator, String elementName) {
			
			List<WebElement> optionsList = null;
			try {				
				Select dp = new Select(driver.findElement(locator));
				optionsList = dp.getOptions();
				return optionsList;
				
			} catch (Exception e) {			
				ExtentReportingUtil.createLogForFail("Unable to read the "+elementName+" dropdown options");
			}
			return optionsList;			
		}
		
		public int getDropdownOptionsCount(By locator, String elementName) {

			int optionsCount = 0;
			try {
				waitThread(minimumWait);
				Select dp = new Select(driver.findElement(locator));
				List<WebElement> optionsList = dp.getOptions();
				optionsCount = optionsList.size();
				return optionsCount;				
			} catch (Exception e) {			
				ExtentReportingUtil.createLogForFail("Unable to read the "+elementName+" dropdown values count");
			}
			return optionsCount;			
		}
		
		public String getDropdownSelectedValue(By locator, String elementName) {
			String selectedValue = null;
			try {
				Select dp = new Select(driver.findElement(locator));
				selectedValue = dp.getFirstSelectedOption().getText().trim();
				waitUtil(minimumWait);
				return selectedValue;
				} catch (Exception e) {
					ExtentReportingUtil.createLogForFail("Unable to read the "+elementName+" dropdown selected value");
				}
			return selectedValue;
			}
		
		public String getDropdownSelectedValue(WebElement locator, String elementName) {
			String selectedValue = null;
			try {
				Select dp = new Select(locator);
				selectedValue = dp.getFirstSelectedOption().getText().trim();
				waitUtil(minimumWait);
				return selectedValue;
				} catch (Exception e) {
					ExtentReportingUtil.createLogForFail("Unable to read the "+elementName+" dropdown selected value");
				}
			return selectedValue;
			}
		
		public void selectByVisibleText(By locator, String dpVisibleText, String elementName) {
			
			try {
				Select dp = new Select(driver.findElement(locator));
				dp.selectByVisibleText(dpVisibleText);
				waitUtil(minimumWait);
				ExtentReportingUtil.createLogForpass("Selected "+dpVisibleText+" from "+elementName+" dropdown successfully");
			} catch (Exception e) {
				ExtentReportingUtil.createLogForFail("Unable to select the "+elementName+" dropdown "+dpVisibleText+" value");
			}
		}
		
		public void setInput(By locator, String inputValue, String elementName) {
			
			try {
				driver.findElement(locator).clear();
				waitThread(minimumWait);
				driver.findElement(locator).sendKeys(inputValue);
				waitThread(minimumWait);
				ExtentReportingUtil.createLogForpass("Successfully entered the "+inputValue+" in "+elementName+" field");
			} catch (Exception e) {
				ExtentReportingUtil.createLogForFail("Unable to enter the "+inputValue+" in "+elementName+" field");
			}
			
		}
		
		public String getAttributeValue(By locator,String attribute, String elementName) {
			
			String attValue = null;
			try {
				attValue = driver.findElement(locator).getAttribute(attribute).trim();
				waitThread(minimumWait);
				return attValue;
			} catch (Exception e) {
				ExtentReportingUtil.createLogForFail("Unable to read the "+elementName+" field "+attribute+" attribute");
			}
			return attValue;
			
		}
		
		public String getText(By locator, String elementName) {
			
			String txt = null;
			try {
				txt = driver.findElement(locator).getText().trim();
				waitThread(minimumWait);
				return txt;
			} catch (Exception e) {
				ExtentReportingUtil.createLogForFail("Unable to read the text from "+elementName);
			}
			return txt;
			
		}
		
		public void textContains(String expectedValue, String actualValue, String elementName) {
			if (expectedValue.contains(actualValue) || actualValue.contains(expectedValue)) {
				ExtentReportingUtil.createLogForpass("Mandatory error message is displaying as expected for "+elementName+" field");
			} else {
				ExtentReportingUtil.createLogForFail("Mandatory error message is not displaying for "+elementName+" field");
				}
			}
		
		public void waitUtil(int milliSecounds) {	
			driver.manage().timeouts().implicitlyWait(milliSecounds,TimeUnit.MILLISECONDS);
		}
		
		public void waitThread(long timeInMillis) {			
			try {
				Thread.sleep(timeInMillis);
			} catch (Exception e) {				
			}			
		}		
	}
