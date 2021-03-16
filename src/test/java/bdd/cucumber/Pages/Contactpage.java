package bdd.cucumber.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import bdd.cucumber.Utilities.CommonUtilities;

public class Contactpage extends CommonUtilities {
	By ele_SubmitButton = By.id("btnSubmit");
	By ele_IamLookingForDropdown = By.id("reachoutforproduct");
	By ele_SelectSubProduct = By.id("product");
	By ele_Complaint = By.id("compInqServ");
	By ele_Product = By.id("need");
	By ele_Emirate = By.id("emirate");
	By ele_Branch = By.id("branch");
	By ele_FirstName = By.id("firstName");
	By ele_Email = By.id("email");
	By ele_mobileNumber = By.id("mobile");
	By ele_mobileNumErrMsg = By.xpath("//div[@id='dvMain']/div/div/div[7]/div/div[2]/span");
	
	public void clickOnSubmitButton() {
		clickElement(ele_SubmitButton, "Submit button in contact us form without entering the values");
		}
	
	//waitThread(minimumWait);
	
	public void verifyErrorMessages() {
	String actualValue;
	String expectedValue = "invalid";
	String attValue = "class";
	actualValue = getAttributeValue(ele_IamLookingForDropdown, attValue, "I am looking for dropdown");
	textContains(expectedValue, actualValue,"I am looking for");
	actualValue = getAttributeValue(ele_Complaint, attValue, "Complaint/Inquiry/Service Type dropdown");
	textContains(expectedValue, actualValue,"Complaint/Inquiry/Service Type dropdown");
	actualValue = getAttributeValue(ele_Product, attValue, "product dropdown");
	textContains(expectedValue, actualValue,"product dropdown");
	actualValue = getAttributeValue(ele_SelectSubProduct, attValue, "Sub product dropdown");
	textContains(expectedValue, actualValue,"Sub product dropdown");
	actualValue = getAttributeValue(ele_Emirate, attValue, "Emirate dropdown");
	textContains(expectedValue, actualValue,"Emirate dropdown");
	actualValue = getAttributeValue(ele_Branch, attValue, "Branch dropdown");
	textContains(expectedValue, actualValue,"Branch dropdown");
	actualValue = getAttributeValue(ele_FirstName, attValue, "First Name");
	textContains(expectedValue, actualValue,"First Name");
	actualValue = getAttributeValue(ele_Email, attValue, "Email");
	textContains(expectedValue, actualValue,"Email");
	 actualValue = getAttributeValue(ele_mobileNumber, attValue, "Mobile number");
	 textContains(expectedValue, actualValue,"Mobile number");
	 }
public int getIamLookingForDropdownOptionsCount() {
	int optCount = getDropdownOptionsCount(ele_IamLookingForDropdown, " I am looking for");
	return optCount-1;
	}
public String getSelectSubProductDpValue() {
	return getDropdownSelectedValue(ele_SelectSubProduct, "Select sub product");
	}
public void selectProductDropdown(String optionVisibleText) {
	selectByVisibleText(ele_Product, optionVisibleText, "Product");
}
public int getSubProductOptionsCount() {
	int optCount = getDropdownOptionsCount(ele_SelectSubProduct, "sub product");
	return optCount-1;
	}
public List<WebElement> getSubProductOptions() {
	return getDropdownOptions(ele_SelectSubProduct, "sub production");
	}
public void setMobileNumber(String number) {
	setInput(ele_mobileNumber, number, "mobile number");
	}
public int getMobileNumberDigitCount() {
	String mobileNumber = getAttributeValue(ele_mobileNumber, "value", "mobile number");
	return mobileNumber.length();
	}
public String getMobileNumErrMsg() {
	return getText(ele_mobileNumErrMsg, "mobile number error message");
	}
public boolean isDisplayNumErrorMessge() {
	return isDisplayElement(ele_mobileNumErrMsg, "mobile number error message");
	}
}


