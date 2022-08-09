package Listener;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import com.relevantcodes.extentreports.ExtentTest;

import Helpers.LaunchDriver;
import Helpers.Screenshots;

public class CustomListener extends LaunchDriver implements ITestListener, IInvokedMethodListener {
	ExtentTest test;

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// Before every method in the Test Class
//		System.out.println("beforeInvocation: " + testResult.getTestClass().getName() + 
//				" => " + method.getTestMethod().getMethodName());
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// After every method in the Test Class
//		System.out.println("afterInvocation: " + testResult.getTestClass().getName() + 
//				" => " + method.getTestMethod().getMethodName());
	}
	

	@Override
	public void onTestStart(ITestResult result) {
		// When test method starts
		// test=report.startTest(result.getName());

	//	System.out.println("Test method starts :" + result.getName());

	}

	

	@Override
	public void onTestSuccess(ITestResult result) {
		// If test method is successful
	}

	@Override
	public void onTestFailure(ITestResult result) {

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// If test method is failed
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Ignore this
	}

	@Override
	public void onStart(ITestContext context) {
		// Before <test> tag of xml file
//		System.out.println("onStart -> Test Tag Name: " + context.getName());
//		ITestNGMethod methods[] = context.getAllTestMethods();
//		System.out.println("These methods will be executed in this test tag:");
//		for (ITestNGMethod method : methods) {
//			System.out.println(method.getMethodName());
//		}
	}

//	@Override
//	public void onFinish(ITestContext context) {
//		// After <test> tag of xml file
//		System.out.println("Test method Ends :" + context.getName());
//	}
	
	

}
