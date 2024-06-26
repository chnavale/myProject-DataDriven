package testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;

public class OpenAccountTest extends BaseTest{
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void openAccount(String customer, String currency) {
		
		
		click("openAccount_XPATH");
		
		select("customer_CSS",customer);
		select("currency_CSS",currency);
		click("process_XPATH");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Alert alert = driver.switchTo().alert();
		
		Assert.assertTrue(alert.getText().contains("Account created successfully"), "Account was not created");
		
		alert.accept();
		
		
		
	}

}
