package rough;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;
import utilities.ExcelReader;

public class LoginTest extends BaseTest{
	
	@Test(dataProviderClass = DataUtil.class, dataProvider="data")
	public void doLogin(String username, String password) {
		
		
		type("username_ID",username);
		type("password_ID",password);
		click("loginBtn_XPATH");
		
		
		
		
	}
	
	

}
