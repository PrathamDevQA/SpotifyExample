package prathamQa.com.spotify.oauth2.api;

import static io.restassured.RestAssured.*;

import java.util.Map;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class RestResources {

	@Step
	public static Response post(String path, String token, Object object) {
		return given(SpecBuild.getRequestSpec()).auth().oauth2(token).body(object).when().post(path).then()
				.spec(SpecBuild.getResponseSpec()).extract().response();
	}

	@Step
	public static Response postAccount(Map<String, String> formParams) {
		return given(SpecBuild.getAccountRequestSpec()).formParams(formParams).when().post(Route.API + Route.TOKEN)
				.then().spec(SpecBuild.getResponseSpec()).extract().response();
	}

	@Step
	public static Response get(String path, String token) {
		return given(SpecBuild.getRequestSpec()).auth().oauth2(token).when().get(path).then()
				.spec(SpecBuild.getResponseSpec()).extract().response();
	}

	@Step
	public static Response put(String path, String token, Object object) {
		return given(SpecBuild.getRequestSpec()).auth().oauth2(token).body(object).when().put(path).then()
				.spec(SpecBuild.getResponseSpec()).extract().response();
	}

}
