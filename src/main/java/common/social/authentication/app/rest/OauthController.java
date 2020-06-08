package common.social.authentication.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.social.authentication.app.rest.response.OauthLoginResponse;
import common.social.authentication.app.rest.response.OauthLoginUrisResponse;
import common.social.authentication.domain.service.oauth.OauthService;
import common.social.authentication.domain.service.oauth.exception.OauthException;

@RestController
@RequestMapping(path = "oauth")
public class OauthController {

    private final OauthService oauthService;

    @Autowired
    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/loginUris")
    public ResponseEntity<OauthLoginUrisResponse> getLoginUris() throws OauthException {
        return ResponseEntity.ok(oauthService.getLoginUris());
    }

    @GetMapping("/success")
    public ResponseEntity<OauthLoginResponse> getUserInfo(OAuth2AuthenticationToken authentication)
            throws OauthException {
        return ResponseEntity.ok(oauthService.getUserInfo(authentication));
    }
//TODO response token y validar el proceso de autorización de endpoints, existe un tokenStore?
}
