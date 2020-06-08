package common.social.authentication.domain.service.oauth.exception;

public class OauthException extends Exception {

    public static final String GENERIC_ERROR_CODE = "OAUTH_ERROR";
    private final String errorCode;

    public OauthException(String message) {
        super(message);
        this.errorCode = GENERIC_ERROR_CODE;
    }

    public OauthException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

}
