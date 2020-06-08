package common.social.authentication.app.rest.response;

import java.util.Map;

public class OauthLoginUrisResponse {

    private Map<String, String> uris;

    public OauthLoginUrisResponse() {
        super();
    }

    public OauthLoginUrisResponse(Map<String, String> uris) {
        this.uris = uris;
    }

    public Map<String, String> getUris() {
        return uris;
    }

}
