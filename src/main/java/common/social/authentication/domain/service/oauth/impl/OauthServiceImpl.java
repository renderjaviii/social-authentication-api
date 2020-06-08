package common.social.authentication.domain.service.oauth.impl;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import common.social.authentication.app.api.TokenApi;
import common.social.authentication.app.rest.response.OauthLoginResponse;
import common.social.authentication.app.rest.response.OauthLoginUrisResponse;
import common.social.authentication.domain.service.oauth.OauthService;
import common.social.authentication.domain.service.oauth.exception.OauthException;

@Service
public class OauthServiceImpl implements OauthService {

    private static final String OAUTH2_BASE_PATH = "oauth/authorize-client/";

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    public OauthServiceImpl(ClientRegistrationRepository clientRegistrationRepository,
                            OAuth2AuthorizedClientService authorizedClientService) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authorizedClientService = authorizedClientService;
    }

    @Override
    public OauthLoginUrisResponse getLoginUris() throws OauthException {
        Iterator<ClientRegistration> iterator = ((InMemoryClientRegistrationRepository) clientRegistrationRepository)
                .iterator();
        Map<String, String> uris = new HashMap<>();
        iterator.forEachRemaining(registration -> uris.put(registration.getClientName(),
                OAUTH2_BASE_PATH + registration.getRegistrationId()));

        if (uris.isEmpty()) {
            throw new OauthException("No authentication providers configured.");
        }
        return new OauthLoginUrisResponse(uris);
    }

    @Override
    public OauthLoginResponse getUserInfo(OAuth2AuthenticationToken authentication) throws OauthException {
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

        String userInfoEndpointUri = client.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        OAuth2AccessToken token = client.getAccessToken();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token.getTokenValue());

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
        Map userAttributes = response.getBody();

        if (response.getStatusCode().equals(HttpStatus.OK) && userAttributes != null) {
            return OauthLoginResponse.newBuilder()
                    .name(userAttributes.get("name"))
                    .firstName(userAttributes.get("given_name"))
                    .lastName(userAttributes.get("family_name"))
                    .picture(userAttributes.get("picture"))
                    .email(userAttributes.get("email"))
                    .emailVerified(userAttributes.get("email_verified"))
                    .locale("locale")
                    .token(TokenApi.newBuilder()
                            .accessToken(token.getTokenValue())
                            .expiresAt(token.getExpiresAt() != null ? token.getExpiresAt().toString() : EMPTY)
                            .tokenType(token.getTokenType().getValue())
                            .build())
                    .build();
        }
        throw new OauthException("User login exception.");
    }

}
