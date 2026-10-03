package Util;

import org.testng.annotations.DataProvider;
import Util.UserData;

public class ApiDataProvider {
	
	@DataProvider(name="userData")
	public Object[][] getUserData() {
		return new Object[][] {
			{ new UserData("John Doe", "Software Engineer") },
			{ new UserData("Jane Smith", "Product Manager") },
			{ new UserData("Alice Johnson", "UX Designer") }
		};
	}
}