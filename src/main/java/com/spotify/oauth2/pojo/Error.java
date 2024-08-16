
package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class Error {

	@JsonProperty("error")
	private InnerError error;

}
