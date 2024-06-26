package rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import utilities.MonitoringMail;
import utilities.TestConfig;

public class TestHostAddress {

	public static void main(String[] args) throws UnknownHostException {
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		MonitoringMail mail = new MonitoringMail();
		
		String body = "http://" + ip + ":8080\\E:\\Workspace\\DataDrivenFramework\\reports";
		
		
		

	}

}
