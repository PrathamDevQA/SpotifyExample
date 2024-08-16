package prathamQa.com.spotify.oauth2.test;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;

public class BaseTest {

	@BeforeMethod
	public void beforeMethods(Method m) {
		System.out.println("Stratint Test: " + m.getName());
		System.out.println("Thread ID: " + Thread.currentThread().getId());
	}
}
