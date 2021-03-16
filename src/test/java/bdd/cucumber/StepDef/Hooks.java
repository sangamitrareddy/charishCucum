package bdd.cucumber.StepDef;

import bdd.cucumber.Utilities.CommonUtilities;
import bdd.cucumber.Utilities.ExtentReportingUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;



public class Hooks extends CommonUtilities{
	public static String scenarioName;
	
	
	@Before
	public void getScenarioName(Scenario scenario)
	{
		scenarioName=scenario.getName();
		ExtentReportingUtil.createExtentTest(scenarioName);
	}
	
	@After
	public void flushReport()
	{
		ExtentReportingUtil.extentflush();
	}

	
}