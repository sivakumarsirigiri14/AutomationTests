package com.hmrc.tests.test.driver.manager;

import com.hmrc.tests.test.configutils.ConfigUtils;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {

	
	public static RemoteWebDriver getDriverDynamic() throws MalformedURLException {
		String defaultBrowser = ConfigUtils.getASpecificProperty("browser");

		RemoteWebDriver driver = null;
		if (defaultBrowser.equals("firefox") || defaultBrowser.equals("ff")) {
			// set driver properties
			String driverPath = new File("").getAbsolutePath() + File.separatorChar + "src"+ File.separatorChar + "test" + File.separatorChar +"resources" + File.separatorChar + "geckodriver";
			System.setProperty("webdriver.gecko.driver",driverPath );
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} 		
		return driver;
	}

}
