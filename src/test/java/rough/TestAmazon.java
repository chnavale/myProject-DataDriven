package rough;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import base.BaseTest;

public class TestAmazon extends BaseTest{
	
	@Test
	public void helloSignin() {
		driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
		mouseHover("accouuntList_CSS");
		click("myAccount_LINK");
	}

}
