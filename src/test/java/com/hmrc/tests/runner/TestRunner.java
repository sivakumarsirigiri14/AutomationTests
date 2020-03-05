package com.hmrc.tests.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/cucumber-html-report","json:target/basicreport.json" }, 
		glue = { "com.hmrc.tests.steps" },
		features = { "classpath:featureFiles/"}
		,tags ={"@login"}
		,monochrome=true
)

public class TestRunner {
	
}
