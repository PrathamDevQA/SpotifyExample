package prathamQa.com.spotify.oauth2.test;

import org.testng.annotations.Test;

import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import prathamQa.com.spotify.oauth2.api.StatusCode;
import prathamQa.com.spotify.oauth2.api.applicationApi.PlaylistApi;
import prathamQa.com.spotify.oauth2.utils.DataLoader;
import prathamQa.com.spotify.oauth2.utils.FakerUtils;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlayListTests extends BaseTest {

	public static Playlist playlistBuilder(String name, String description, boolean publics) {
		return Playlist.builder().name(name).description(description)._public(publics).build();
	}

	public void assertPlayList(Playlist playListResponse, Playlist playListRequest) {
		assertThat(playListResponse.getName(), equalTo(playListRequest.getName()));
		assertThat(playListResponse.getDescription(), equalTo(playListRequest.getDescription()));
		assertThat(playListResponse.get_public(), equalTo(playListRequest.get_public()));
	}

	public void assertError(Error responseErr, StatusCode statusCode) {
		assertThat(responseErr.getError().getStatus(), equalTo(statusCode.code));
		assertThat(responseErr.getError().getMessage(), equalTo(statusCode.msg));
	}

	public void assertStatusCode(int actualStatusCode, StatusCode statusCode) {
		assertThat(actualStatusCode, equalTo(statusCode.code));
	}

	@Story("Create a playlist story")
	@Link("https://example.org")
	@Link(name = "allure", type = "mylink")
	@TmsLink("12345")
	@Issue("1234567")
	@Description("this is the description")
	public void shouldBeAbleToCreataPlaylist() {

		Playlist reqPlaylist = playlistBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);

		Response response = PlaylistApi.post(reqPlaylist);
//		assertThat(response.statusCode(), equalTo(201));

		Playlist responsePlaylist = response.as(Playlist.class);

		assertStatusCode(response.statusCode(), StatusCode.CODE_201);
		assertPlayList(responsePlaylist, reqPlaylist);
	}

	@Step
	@Test(testName = "Should be able to get a created Playlist")
	public void getCreatedPlaylist() {

		Playlist reqPlaylist = playlistBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), true);

		Response response = PlaylistApi.get(DataLoader.getInstanceData().getPlayListId());
//		assertThat(response.statusCode(), equalTo(200));

		Playlist responsePlaylist = response.as(Playlist.class);

		assertStatusCode(response.statusCode(), StatusCode.CODE_200);
		assertPlayList(responsePlaylist, reqPlaylist);
	}

	@Step
	@Test(testName = "Should be able to create a Playlist")
	public void shouldBeAbleToUpdatePlaylist() {

		Playlist reqPlaylist = playlistBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);

		/*
		 * Playlist playlist = new Playlist().setName("New Playlist Two").
		 * setDescription("NEW Playlist Description") .setPublic(false);
		 */

		Response response = PlaylistApi.put(DataLoader.getInstanceData().getUpdatePlayListId(), reqPlaylist);

//		assertThat(response.statusCode(), equalTo(200));
		assertStatusCode(response.statusCode(), StatusCode.CODE_200);

	}

	@Step
	@Test
	public void shouldNotBeAbleToCreataPlaylistWithoutName() {
		Playlist playlist = playlistBuilder("", FakerUtils.generateDescription(), false);
//		Playlist playlist = new Playlist().setName("").setDescription("NEW Playlist Description").setPublic(false);

		Response response = PlaylistApi.post(playlist);
//		assertThat(response.statusCode(), equalTo(400));

		Error responseError = response.as(Error.class);

		assertStatusCode(response.statusCode(), StatusCode.CODE_400);
		assertError(responseError, StatusCode.CODE_400);

	}

	@Step
	@Test
	public void shouldBeAbleToCreataPlaylistWithInvalidToken() {

		String invalid_token = "12343534";
		Playlist reqPlaylist = playlistBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);
		/*
		 * Playlist playlist = new Playlist().setName("New Playlist one").
		 * setDescription("NEW Playlist Description one") .setPublic(false);
		 */
		Response response = PlaylistApi.post(invalid_token, reqPlaylist);
//		assertThat(response.statusCode(), equalTo(401));
		Error responseError = response.as(Error.class);

		assertStatusCode(response.statusCode(), StatusCode.CODE_401);
		assertError(responseError, StatusCode.CODE_401);

	}
}
