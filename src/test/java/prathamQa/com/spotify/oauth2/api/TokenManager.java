package prathamQa.com.spotify.oauth2.api;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import prathamQa.com.spotify.oauth2.utils.ConfigLoader;

public class TokenManager {

	public static String access_key;
	public static Instant expire_time;

	public synchronized static String getToken() {
		try {
			if (access_key == null || Instant.now().isAfter(expire_time)) {
				System.out.println("Renew token....!!!!");
				Response response = reNewToken();
				access_key = response.path("access_token");
				int expiryDuration = response.path("expires_in");
				expire_time = Instant.now().plusSeconds(expiryDuration - 200);
			} else {
				System.out.println("Token is Good To USE......");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("ABORT!!! Failed to get token");
		}
		return access_key;

	}

	public static Response reNewToken() {
		Map<String, String> formParams = new HashMap<String, String>();
		formParams.put("client_id", ConfigLoader.getInstace().getClintId());
		formParams.put("client_secret", ConfigLoader.getInstace().getClintSecret());
		formParams.put("grant_type", ConfigLoader.getInstace().getGrantType());
		formParams.put("refresh_token", ConfigLoader.getInstace().getRefreshToken());

		Response response = RestResources.postAccount(formParams);

		if (response.statusCode() != 200) {
			throw new RuntimeException("Abort !!!!, Renew the Token");
		}

		return response;

	}

}
