package bdd.cucumber.StepDef;

import java.util.List;

import org.openqa.selenium.WebElement;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import bdd.cucumber.Pages.Contactpage;
import bdd.cucumber.Utilities.ExtentReportingUtil;

public class Contactpage_SD  {
	Contactpage getContactPage = new Contactpage();

	
	@And("Submit the form without entering any details")
	public void submit_the_form_without_entering_any_details() {
		
		getContactPage.clickOnSubmitButton();
		
	}
	
	@Then("Error messages should display for required fields")
	public void error_messages_should_display_for_required_fields() {
		
		getContactPage.verifyErrorMessages();
		
	}
	
	@And("I am looking to field is a dropdown with (.*) choices")
	public void i_am_looking_to_field_is_a_dropdown_with_iIamLookingForOptions_choices(int expectedOptionsCount){
		
		int actualOptionsCount = getContactPage.getIamLookingForDropdownOptionsCount();
		if (actualOptionsCount == expectedOptionsCount) {
			ExtentReportingUtil.createLogForpass("I am looking to dropdown is displaying expected "+actualOptionsCount+" choices");
		} else {
			ExtentReportingUtil.createLogForFail("I am looking to dropdown is not displaying expected "+expectedOptionsCount+" choices."
					+ "And actual choices displaying in application are "+actualOptionsCount);
		}
		
	}
	
	@And("Initially the Select Sub Product field is should display empty option (.*)")
	public void initially_the_Select_Sub_Product_field_is_should_display_empty_option(String expSelectedSubProductOption) {
		
		String actualSubProduct = getContactPage.getSelectSubProductDpValue();
		
		if (actualSubProduct.equals(expSelectedSubProductOption)) {
			ExtentReportingUtil.createLogForpass("Initially the Select Sub Product field is displaying empty option");
		} else {
			ExtentReportingUtil.createLogForFail("Initially the Select Sub Product field is not displaying empty option");
		}
		
	}
	
	@And("Select the Product (.*) from the dropdown")
	public void select_the_Product_productOption_from_the_dropdown(String productOption) {
		
		getContactPage.selectProductDropdown(productOption);
		
	}
	
	@Then("Select Sub Product dropdown populates with (.*) and including (.*)")
	public void select_Sub_Product_dropdown_populates_with_Count_and_including_subProductOption(int expSubProductOptionsCount, String subProductOption){
		
		int actualSubProductOptionsCount = getContactPage.getSubProductOptionsCount();
		
		if (actualSubProductOptionsCount == expSubProductOptionsCount) {
			ExtentReportingUtil.createLogForpass("Sub product is displaying "+actualSubProductOptionsCount+" options");
		} else {
			
			ExtentReportingUtil.createLogForFail("Sub product is not displaying "+actualSubProductOptionsCount+" options");

		}
		
		List<WebElement> actualSPDpList = getContactPage.getSubProductOptions();
		
		int c=0;
		for (int i = 0; i < actualSPDpList.size(); i++) {
			
			String actSPDpVal = actualSPDpList.get(i).getText().trim();
			
			if (actSPDpVal.equals(subProductOption)) {
				c=0;
				ExtentReportingUtil.createLogForpass(subProductOption+" option is visible in Sub product dropdown");
				break;
			} else {
				c=c+1;
			}
			
		}
		
		if (c!=0) {			
			ExtentReportingUtil.createLogForFail(subProductOption+" option is not visible in Sub product dropdown");
		}
			
		
	}
	
	@And("Entering a number (.*) that is less than (.*) digits in the Mobile Number field should show an error (.*)")
	public void entering_a_number_that_is_less_than_digitCount_digits_in_the_Mobile_Number_field_should_show_an_error(String number, int expDigitCount, String expErrMsg){
		
		getContactPage.setMobileNumber(number);
		
		int actualDigitCount = getContactPage.getMobileNumberDigitCount();
		
		if (actualDigitCount < expDigitCount) {
			
			String actualMobileErrMsg = getContactPage.getMobileNumErrMsg();
			
			if (actualMobileErrMsg.equals(expErrMsg)) {
				
				ExtentReportingUtil.createLogForpass("If number is less than "+expDigitCount+" digits then mobile number field is displaying '"
														+actualMobileErrMsg+"' valid error message");
			} else {
				
				ExtentReportingUtil.createLogForFail("If number is less than "+expDigitCount+" digits then mobile number field is not displaying '"
						+actualMobileErrMsg+"' valid error message");

			}
			
		}
		
		else if (actualDigitCount == expDigitCount) {
			
			if (!getContactPage.isDisplayNumErrorMessge()) {
				
				ExtentReportingUtil.createLogForpass("If mobile number is equals to "+expDigitCount+" digits then mobile number field is not displaying error message");
			} else {

				ExtentReportingUtil.createLogForpass("If mobile number is equals to "+expDigitCount+" digits then mobile number field is displaying error message");
				
			}
			
		}
		
	}
	
	@And("Entering a mobile number (.*) that is (.*) digits should not show an error (.*)")
	public void entering_a_mobile_number_that_is_digits_should_not_show_an_error(String mobileNumber, int digitCount, String expErrMsg) {
		
		entering_a_number_that_is_less_than_digitCount_digits_in_the_Mobile_Number_field_should_show_an_error(mobileNumber, digitCount, expErrMsg);
		
	}
	
	
	
	

}


