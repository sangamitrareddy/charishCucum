package bdd.cucumber.StepDef;

import java.util.List;

import org.openqa.selenium.WebElement;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import bdd.cucumber.Utilities.CommonUtilities;
import bdd.cucumber.Utilities.ExtentReportingUtil;
import bdd.cucumber.Pages.Homepage;

public class Homepage_SD extends CommonUtilities {
	Homepage getHomePage = new Homepage();
	
	@Given("User is on mashre bank home page")
	public void user_is_on_mashre_bank_home_page() {
		try {
			openBrowser();
			} catch (InterruptedException e) {
				ExtentReportingUtil.createLogForFail("Unable to launch the application");
				}
	}

	@And("The navigation bar should be visible on desktop devices")
	public void the_navigation_bar_should_be_visible_on_desktop_devices() {
		if (getHomePage.isDisplayMenuList()) {
			ExtentReportingUtil.createLogForpass("Navigation bar is displaying on desktop devices");
			} else {
				ExtentReportingUtil.createLogForFail("Navigation bar is not displaying on desktop devices");
				}
	}
	@And("Navigation bar should display items (.*)")
	public void navigation_bar_should_display_menuList_items(String menuListItems){
		String expectedMenuListItems = properties.getProperty(menuListItems);
		String[] expectedMenuListItemsArr = expectedMenuListItems.split(";");
		List<WebElement> actualMenuItemsList = getHomePage.getMenuList();
		int c = 0;
		for (int i = 0; i < expectedMenuListItemsArr.length; i++) {
			int count = 0;
			String expectedMenuItem = expectedMenuListItemsArr[i].trim();
			for (int j = 1; j < actualMenuItemsList.size(); j++) {
				String actualMenuItem = actualMenuItemsList.get(j).getText();
				if (expectedMenuItem.equals(actualMenuItem)) {
					break;
					} else {
						count=count+1;
						}
				}
			if (count==actualMenuItemsList.size()-1) {
				ExtentReportingUtil.createLogForFail(expectedMenuListItemsArr[i]+" menu item is not displaying in navigation bar");
				c=c+1;
				}
			}
		if (c==0) {
			ExtentReportingUtil.createLogForpass("Navigation bar is displaying all 9 items successfully");
			}
	}
	@And("Mashreq News should be displayed on the homepage")
	public void mashreq_News_should_be_displayed_on_the_homepage() {
		boolean isDisplayMashreqNews = getHomePage.isDisplayMashreqNews();
		boolean isDisplayViewAllNews = getHomePage.isDisplayViewAllNews();
		if ((!isDisplayMashreqNews) && (!isDisplayViewAllNews)) {
			ExtentReportingUtil.createLogForFail("Mashreq News and view all news are not displaying in home page");
			}else {
				ExtentReportingUtil.createLogForpass("Mashreq News and view all news are displaying successfully in home page");
				}
		}
	@And("A link for Contact Us should be displayed on the homepage")
	public void a_link_for_Contact_Us_should_be_displayed_on_the_homepage() {
		if (getHomePage.isDisplayContactUsLink()) {
			ExtentReportingUtil.createLogForpass("Contact Us is displaying on the home page");
			} else {
				ExtentReportingUtil.createLogForFail("Contact Us is not displaying on the home page");
				}
	}
		@And("Click on the contact us link")
		public void click_on_the_contact_us_link() {
			getHomePage.clickOnContactUs();
			}
		@Then("Contact form page will display")
		public void contact_form_page_will_display() {
			boolean actIsDisplayContactUsLink = getHomePage.isDisplayContactUsForm();
			if (actIsDisplayContactUsLink) {
				ExtentReportingUtil.createLogForpass("Contact Us form is displaying successfully");
				} else {
					ExtentReportingUtil.createLogForFail("Contact Us form is not displaying");
					}
		}
		@And("Close the browser")
		public void close_the_browser() throws Throwable {
			driver.close();
			driver.quit();
			}
		}
		


