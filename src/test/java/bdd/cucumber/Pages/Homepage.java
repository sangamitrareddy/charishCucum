package bdd.cucumber.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import bdd.cucumber.Utilities.CommonUtilities;

public class Homepage extends CommonUtilities {

	By ele_navigationBar = By.className("leftLinks");
	By ele_menuList = By.xpath("//div[@class='leftLinks']/ul/li/a");
	By ele_mashreqNews = By.xpath("//div[@class='newsBox']/h3[contains(text(),' Mashreq News')]");
	By ele_viewAllNews = By.linkText("View all news");
	By ele_contactUsTopMenu = By.xpath("//a/p[contains(text(),'Contact')]");
	By ele_contactUsForm = By.id("form");

	public String geturl() {
		String str = driver.getTitle();
		return str;
		}
	public boolean isDisplayMenuList() {
		return isDisplayElement(ele_navigationBar, "Menu items");
		}
	public List<WebElement> getMenuList() {
		return getAllElementsInPage(ele_menuList, "Menu options");
		}
	public boolean isDisplayMashreqNews() {
		moveToElement(ele_mashreqNews, "Mashreq News");
		waitThread(minimumWait);
		return isDisplayElement(ele_mashreqNews, "Mashreq News");
		}
	public boolean isDisplayViewAllNews() {
		moveToElement(ele_viewAllNews, "Mashreq News");
		waitThread(minimumWait);
		return isDisplayElement(ele_viewAllNews, "View All Mashreq News");
		}
	public boolean isDisplayContactUsLink() {
		return isDisplayElement(ele_contactUsTopMenu, "Contact Us link");
		}
	public void clickOnContactUs() {
		clickElement(ele_contactUsTopMenu, "Contact Us link");
		}
	public boolean isDisplayContactUsForm() {
		return isDisplayElement(ele_contactUsForm, "Contact Us form");
		}

}
