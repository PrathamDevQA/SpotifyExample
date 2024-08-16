package prathamQa.com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.pojo.Playlist;

import io.restassured.response.Response;
import prathamQa.com.spotify.oauth2.api.RestResources;
import prathamQa.com.spotify.oauth2.api.TokenManager;
import prathamQa.com.spotify.oauth2.utils.ConfigLoader;

import static prathamQa.com.spotify.oauth2.api.Route.*;

public class PlaylistApi {

	public static Response post(Playlist playlist) {
		return RestResources.post(USERS + "/" + ConfigLoader.getInstace().getUserId() + PLAYLISTS,
				TokenManager.getToken(), playlist);

	}

	public static Response post(String token, Playlist playlist) {

		return RestResources.post(USERS + ConfigLoader.getInstace().getUserId() + PLAYLISTS, token, playlist);

	}

	public static Response get(String playlistId) {
		return RestResources.get(PLAYLISTS + "/" + playlistId, TokenManager.getToken());
	}

	public static Response put(String playlistId, Playlist playlist) {
		return RestResources.put(PLAYLISTS + "/" + playlistId, TokenManager.getToken(), playlist);

	}

}
