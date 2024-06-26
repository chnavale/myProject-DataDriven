package testcases;

import java.util.HashMap;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;

public class AddCustomerTest extends BaseTest{
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void addCustomer(String firstName, String lastName, String postCode){
		
		
		click("addCustBtn_XPATH");
		type("firstName_CSS",firstName);
		type("lastName_CSS",lastName);
		type("postCode_CSS", postCode);
		click("addCustSubmit_CSS");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Alert alert = driver.switchTo().alert();
		
		Assert.assertTrue(alert.getText().contains("Customer added successfully"),"Customer not added successfully");
		
		alert.accept();
		
	}

}
