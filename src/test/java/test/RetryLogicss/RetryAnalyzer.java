package RetryLogicss;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	int count=0;
	int max=3;
	public boolean retry(ITestResult result) {
		
		if(count < max) {
			count++;
			return true;
		}
		
		return false;
	}

}
