package rough;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;

public class UserRegTest extends BaseTest{
	
	@Test(dataProviderClass = DataUtil.class, dataProvider="data")
	public void doUserReg(String firstName, String lastName, String address) {
		
		System.out.println(firstName+"/"+lastName+"/"+address);
		
	}
	
	
}
