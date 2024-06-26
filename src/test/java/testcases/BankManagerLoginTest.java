package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class BankManagerLoginTest extends BaseTest {
	
	@Test
	public void loginBankManager() {
		
		click("bml_XPATH");
		Assert.assertTrue(isElementPresent("addCustBtn_XPATH"), "Bank manager login failed");
		
		
	}

}
