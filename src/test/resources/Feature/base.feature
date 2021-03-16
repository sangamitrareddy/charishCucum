@MASHREQBankTestSuite
Feature: Verify the mashre bank features

@TestCase01
Scenario Outline: Verify the home page
	Given User is on mashre bank home page
	And The navigation bar should be visible on desktop devices
	And Navigation bar should display items <menuList>
	And Mashreq News should be displayed on the homepage
	And A link for Contact Us should be displayed on the homepage
	And Click on the contact us link
	Then Contact form page will display
	And Close the browser
	
	Examples:
	|menuList|
	|menuListItems|

@TestCase02
Scenario Outline: Verify the contact form page
	Given User is on mashre bank home page
	When Click on the contact us link
	Then Contact form page will display
	And Submit the form without entering any details
	Then Error messages should display for required fields
	And I am looking to field is a dropdown with <iIamLookingForOptions> choices
	And Initially the Select Sub Product field is should display empty option <selectedSubProductOption>
	And Select the Product <productOption> from the dropdown
	Then Select Sub Product dropdown populates with <subProductOptionsCount> and including <subProductOption>
	And Entering a number <mobileNumberInvalid> that is less than <digitCount> digits in the Mobile Number field should show an error <mobileNumErrMsg>
	And Entering a mobile number <mobileNumberValid> that is <digitCount> digits should not show an error <mobileNumErrMsg>
	And Close the browser
	
	Examples:
	|iIamLookingForOptions|selectedSubProductOption|productOption|subProductOptionsCount|subProductOption|digitCount|mobileNumErrMsg|mobileNumberInvalid|mobileNumberValid|
	|4|-- Select Sub Product --|Loans|6|Home Loan UAE Resident|7|Mobile number should be 7 digit|123456|1234567|