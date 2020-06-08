package common.social.authentication.domain.service.oauth;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import common.social.authentication.app.rest.response.OauthLoginResponse;
import common.social.authentication.app.rest.response.OauthLoginUrisResponse;
import common.social.authentication.domain.service.oauth.exception.OauthException;

public interface OauthService {

    OauthLoginUrisResponse getLoginUris() throws OauthException;

    OauthLoginResponse getUserInfo(OAuth2AuthenticationToken authentication) throws OauthException;

}
