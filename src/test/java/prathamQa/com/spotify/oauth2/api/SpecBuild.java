package prathamQa.com.spotify.oauth2.api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuild {

	@Step
	public static RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder().setBaseUri("https://api.spotify.com").addFilter(new AllureRestAssured())
				.setBasePath(Route.BASE_PATH).setContentType(ContentType.JSON).log(LogDetail.ALL).build();

	}

	@Step
	public static RequestSpecification getAccountRequestSpec() {
		return new RequestSpecBuilder().setBaseUri("https://accounts.spotify.com").addFilter(new AllureRestAssured())
				.setContentType(ContentType.URLENC).log(LogDetail.ALL).build();

	}

	@Step
	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder().log(LogDetail.ALL).build();

	}

}
