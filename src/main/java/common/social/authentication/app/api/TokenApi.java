package common.social.authentication.app.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("token")
public class TokenApi {

    @JsonProperty
    private String accessToken;

    @JsonProperty
    private String tokenType;

    @JsonProperty
    private String refreshToken;

    @JsonProperty
    private String expiresAt;

    @JsonProperty
    private String scope;

    @JsonProperty
    private String jti;

    public TokenApi() {
        super();
    }

    private TokenApi(Builder builder) {
        accessToken = builder.accessToken;
        tokenType = builder.tokenType;
        refreshToken = builder.refreshToken;
        expiresAt = builder.expiresAt;
        scope = builder.scope;
        jti = builder.jti;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public String getScope() {
        return scope;
    }

    public String getJti() {
        return jti;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private String accessToken;
        private String tokenType;
        private String refreshToken;
        private String expiresAt;
        private String scope;
        private String jti;

        private Builder() {
            super();
        }

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder tokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder expiresAt(String expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public Builder jti(String jti) {
            this.jti = jti;
            return this;
        }

        public TokenApi build() {
            return new TokenApi(this);
        }

    }

}
