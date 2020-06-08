package common.social.authentication.app.rest.response;

import common.social.authentication.app.api.TokenApi;

public class OauthLoginResponse {

    private Object name;
    private Object firstName;
    private Object lastName;
    private Object picture;
    private Object email;
    private Object emailVerified;
    private Object locale;

    private TokenApi token;

    public OauthLoginResponse() {
        super();
    }

    private OauthLoginResponse(Builder builder) {
        name = builder.name;
        firstName = builder.firstName;
        lastName = builder.lastName;
        picture = builder.picture;
        email = builder.email;
        emailVerified = builder.emailVerified;
        locale = builder.locale;
        token = builder.token;
    }

    public Object getName() {
        return name;
    }

    public Object getFirstName() {
        return firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public Object getPicture() {
        return picture;
    }

    public Object getEmail() {
        return email;
    }

    public Object getEmailVerified() {
        return emailVerified;
    }

    public Object getLocale() {
        return locale;
    }

    public TokenApi getToken() {
        return token;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private Object name;
        private Object firstName;
        private Object lastName;
        private Object picture;
        private Object email;
        private Object emailVerified;
        private Object locale;
        private TokenApi token;

        private Builder() {
            super();
        }

        public Builder name(Object name) {
            this.name = name;
            return this;
        }

        public Builder firstName(Object firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(Object lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder picture(Object picture) {
            this.picture = picture;
            return this;
        }

        public Builder email(Object email) {
            this.email = email;
            return this;
        }

        public Builder emailVerified(Object emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        public Builder locale(Object locale) {
            this.locale = locale;
            return this;
        }

        public Builder token(TokenApi token) {
            this.token = token;
            return this;
        }

        public OauthLoginResponse build() {
            return new OauthLoginResponse(this);
        }

    }

}
